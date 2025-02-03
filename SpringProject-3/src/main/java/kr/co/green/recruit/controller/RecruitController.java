package kr.co.green.recruit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.green.recruit.dto.PageInfoDTO;
import kr.co.green.recruit.dto.RecruitDTO;
import kr.co.green.recruit.service.RecruitServiceImpl;
import kr.co.green.recruit.util.PageNation;

@Controller
@RequestMapping("/MatchMyduo")
public class RecruitController {

	private final RecruitServiceImpl recruitService;
	private final PageNation pageNation;
	
	
	public RecruitController(RecruitServiceImpl recruitService,PageNation pageNation) {
		this.recruitService = recruitService;
		this.pageNation = pageNation;
	}
	// RGAPI-faa24592-2f88-459c-91b5-a2c1fb607fad
	
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
			System.out.println(item.getLolWin());
			System.out.println(item.getLolLose());
		}
		
		model.addAttribute("posts",postsResult);
		model.addAttribute("pi",piResult);
		
		
		
		return "recruit/recruit";
	}
	
	@PostMapping("/enroll")
	public String enroll(RecruitDTO recruitDTO,@SessionAttribute("userNo") int userNo) {
		System.out.println(recruitDTO);
		recruitDTO.getRegisterDTO().setUserNo(userNo);
		recruitService.partyEnroll(recruitDTO);
		return "redirect:/MatchMyduo/recruit";
	}
}
