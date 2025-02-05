package kr.co.green.api.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

/*
 * import org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import kr.co.green.api.service.RiotApiService;
 * 
 * @RestController
 * 
 * @RequestMapping("/riot/api") public class RiotApiController {
 * 
 * private final RiotApiService riotApiService;
 * 
 * public RiotApiController(RiotApiService riotApiService) { this.riotApiService
 * = riotApiService; }
 * 
 * @GetMapping("/league/entries/{summonerId}") public ResponseEntity<String>
 * getLeagueEntries(@PathVariable("summonerId") String summonerId) { String
 * response = riotApiService.getLeagueEntriesBySummonerId(summonerId); return
 * ResponseEntity.ok(response); } }
 */





import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.green.recruit.dto.RecruitDTO;

@RestController
@RequestMapping("/api/riot")
public class RiotApiController {

    private static final String API_KEY = "RGAPI-0ba3a3c8-7ea3-46f5-95c9-1bb4a4e27ccc"; // API 키 적용

    private final RestTemplate restTemplate = new RestTemplate();

    // 🔹 1. 소환사 정보 가져오기
    @GetMapping("/account/{gameName}/{tagLine}")
    public ResponseEntity<String> getSummonerInfo(@PathVariable("gameName") String gameName, @PathVariable("tagLine") String tagLine) {
        String url = String.format("https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/%s/%s?api_key=%s",
                gameName, tagLine, API_KEY);  
        
        return fetchFromRiotApi(url);
        
    }

    // 🔹 2. PUUID를 기반으로 최근 경기 목록 가져오기
    @GetMapping("/matches/{puuid}")
    public ResponseEntity<String> getMatches(@PathVariable("puuid") String puuid) {
        String url = String.format("https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/%s/ids?start=0&count=20&api_key=%s",
                puuid, API_KEY);
        return fetchFromRiotApi(url);
    }

    // 🔹 3. 특정 경기 정보 가져오기
    @GetMapping("/match/{matchId}")
    public ResponseEntity<String> getMatchInfo(@PathVariable("matchId") String matchId) {
    	HttpHeaders headers = new HttpHeaders(); headers.add("Accept","*/*");
    	HttpEntity<String> entity = new HttpEntity<String>("", headers);
    	String url = String.format("https://asia.api.riotgames.com/lol/match/v5/matches/%s?api_key=%s",
                matchId, API_KEY);
        return requestTest(url, entity);
    }

    // 🔹 4. 소환사 랭크 정보 가져오기
    @GetMapping("/league/entries/{summonerId}")
    public ResponseEntity<String> getLeagueEntries(@PathVariable("summonerId") String summonerId) {
    	HttpHeaders headers = new HttpHeaders(); headers.add("Accept","*/*");
    	HttpEntity<String> entity = new HttpEntity<String>("", headers);
        String url = String.format("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/%s?queue=RANKED_SOLO_5x5&api_key=%s",
                summonerId, API_KEY);
        
        System.out.println(summonerId);
        
        
        return requestTest(url, entity);
    }
    
    public ResponseEntity<String> requestTest(String url, HttpEntity<String> entity) {
    	ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    	return response;
    }

    // 공통 Riot API 요청 메서드
    private ResponseEntity<String> fetchFromRiotApi(String url) {
        return restTemplate.getForEntity(url, String.class);
    }
    

    

    
    
    
    

    
    
    
    
    
    
}

