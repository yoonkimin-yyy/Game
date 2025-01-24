package kr.co.green.api.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RiotApiServiceImpl implements RiotApiService {

    private static final String API_KEY = "RGAPI-2405bbcf-a7b7-4426-80c9-8eabe10faa0d";
    private static final String BASE_URL = "https://kr.api.riotgames.com";

    private final RestTemplate restTemplate;

    public RiotApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getLeagueEntriesBySummonerId(String summonerId) {
        String url = BASE_URL + "/lol/league/v4/entries/by-summoner/" + summonerId;

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", API_KEY);

        // 요청 보내기
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}