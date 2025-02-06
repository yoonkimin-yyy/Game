package kr.co.green.recruit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.green.api.controller.RiotApiController;
import kr.co.green.recruit.dto.PageInfoDTO;
import kr.co.green.recruit.dto.RecruitDTO;
import kr.co.green.recruit.service.RecruitServiceImpl;
import kr.co.green.recruit.util.PageNation;

@Controller
@RequestMapping("/MatchMyduo")
public class RecruitController {

	private final RecruitServiceImpl recruitService;
	private final PageNation pageNation;
	private final RiotApiController riotApiController;
	
	// api키 
	private static final String API_KEY = "RGAPI-69e9e891-66cb-4b93-8ce2-83022ae2a952";
	
	private static final String REGION = "kr";
	
	public RecruitController(RecruitServiceImpl recruitService,PageNation pageNation, RiotApiController riotApiController) {
		this.recruitService = recruitService;
		this.pageNation = pageNation;
		this.riotApiController = riotApiController;
	}
	
	@GetMapping("/recruit")
	public String recruit(@RequestParam(value="currentPage",defaultValue="1")int currentPage,
						  Model model) {
		
		int postCount = recruitService.totalCount();
		int pageLimit = 5;
		int boardLimit = 10;
		
		Map<String,Object> result = recruitService.recruit(pageNation, currentPage, postCount, pageLimit, boardLimit);
		
		PageInfoDTO piResult = (PageInfoDTO) result.get("pi");
		List<RecruitDTO> postsResult = (List<RecruitDTO>) result.get("posts");
		for(RecruitDTO item : postsResult) {
			 String getPuuidUrl = String.format("https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/%s?api_key=%s",
		                item.getRiotName(), item.getRiotTag(), API_KEY);  
			ResponseEntity<String> getPuuidResponse = riotApiController.fetchFromRiotApi(getPuuidUrl);
			String puuid = extractPuuidFromResponse(getPuuidResponse, "puuid");
			
			String getIconUrl = String.format("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/%s?api_key=%s",
	    			puuid, API_KEY);
			ResponseEntity<String> getIconResponse = riotApiController.fetchFromRiotApi(getIconUrl);
			String profileIconId = extractPuuidFromResponse(getIconResponse, "profileIconId");
			
			item.setProfileIconUrl("http://ddragon.leagueoflegends.com/cdn/15.3.1/img/profileicon/" + profileIconId +".png");
		}
		
		System.out.println("current Page: " + currentPage);
		System.out.println("total posts: " + postCount);
		System.out.println("posts retrievedL " + result.get(postsResult));
		
		model.addAttribute("posts",postsResult);
		model.addAttribute("pi",piResult);
		
//		RiotApiController riot = new RiotApiController(); 
//		System.out.println(riot.getProfile());
		
		
		return "recruit/recruit";
	}
	
	@GetMapping("/api/recruit")
    public ResponseEntity<Map<String, Object>> apiRecruit(
        @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {

        int boardLimit = 10;
        Map<String, Object> result = recruitService.recruit(pageNation, currentPage, boardLimit, boardLimit, boardLimit);

        // 🔴 [디버깅] 서버 응답이 정상적인지 확인
        System.out.println("Current Page: " + currentPage);
        System.out.println("Posts Retrieved: " + result.get("posts"));
        
        for(RecruitDTO item : (List<RecruitDTO>)result.get("posts")) {
			 String getPuuidUrl = String.format("https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/%s?api_key=%s",
		                item.getRiotName(), item.getRiotTag(), API_KEY);  
			ResponseEntity<String> getPuuidResponse = riotApiController.fetchFromRiotApi(getPuuidUrl);
			String puuid = extractPuuidFromResponse(getPuuidResponse, "puuid");
			
			String getIconUrl = String.format("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/%s?api_key=%s",
	    			puuid, API_KEY);
			ResponseEntity<String> getIconResponse = riotApiController.fetchFromRiotApi(getIconUrl);
			String profileIconId = extractPuuidFromResponse(getIconResponse, "profileIconId");
			
			item.setProfileIconUrl("http://ddragon.leagueoflegends.com/cdn/15.3.1/img/profileicon/" + profileIconId +".png");
		}

        return ResponseEntity.ok(result);
    }
	
	// 소환사 아이콘 이미지 가져오는 코드 추가
	
	
	

	

	
	@PostMapping("/enroll")
	public String enroll(RecruitDTO recruitDTO,@SessionAttribute("userNo") int userNo) {
		System.out.println(recruitDTO);
		recruitDTO.getRegisterDTO().setUserNo(userNo);
		recruitService.partyEnroll(recruitDTO);
		return "redirect:/MatchMyduo/recruit";
	}
	
	public String extractPuuidFromResponse(ResponseEntity<String> response, String type) {
	    try {
	        // JSON 응답을 파싱하기 위한 ObjectMapper
	        ObjectMapper objectMapper = new ObjectMapper();
	        
	        // 응답 본문을 JsonNode 객체로 변환
	        JsonNode jsonResponse = objectMapper.readTree(response.getBody());
	        String getStr = "";
	        // 'puuid' 값을 추출
	        if(type.equals("puuid")) {
	        	getStr = jsonResponse.get("puuid").asText();  // puuid는 문자열 타입으로 반환됨
	        } else if(type.equals("profileIconId")) {
	        	getStr = jsonResponse.get("profileIconId").asText(); 
	        }
	        
	        return getStr;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
