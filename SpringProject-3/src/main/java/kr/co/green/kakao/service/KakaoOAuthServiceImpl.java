package kr.co.green.kakao.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import kr.co.green.kakao.dto.KakaoUserDTO;

@Service
public class KakaoOAuthServiceImpl implements KakaoOAuthService {

	@Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    @Value("${kakao.token_uri}")
    private String tokenUri;

    @Value("${kakao.user_info_uri}")
    private String userInfoUri;
    
   
   
    // 카카오 엑세스 토큰 요청
    @Override
    public String getAccessToken(String code) {
    	RestTemplate restTemplate = new RestTemplate();
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	
    	MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
    	params.add("grant_type", "authorization_code");
    	params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        
      
        // 카카오톡 토큰 요청 api 호출
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUri, request, String.class);
        
        System.out.println("카카오 토큰 API 응답: " + response.getBody());

        
        JSONObject jsonObject = new JSONObject(response.getBody());
        
        System.out.println("카카오 액세스 토큰: " + jsonObject.getString("access_token"));

        return jsonObject.getString("access_token");

        
    }
    // 카카오 사용자 정보 요청
    @Override
    public KakaoUserDTO getUserInfo(String accessToken) {
    	 RestTemplate restTemplate = new RestTemplate();
    	 HttpHeaders headers = new HttpHeaders();
    	 headers.set("Authorization", "Bearer " + accessToken);
    	 headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	 
    	 HttpEntity<Void> request = new HttpEntity<>(headers);
    	 
    	
    	 ResponseEntity<String> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, String.class);
    	 
    	 System.out.println("카카오 사용자 정보 API 응답: " + response.getBody());
    	 
    	 JSONObject jsonObject = new JSONObject(response.getBody());
    	 
    	 
    	 System.out.println(jsonObject);
    	 
    	 KakaoUserDTO kakaoUser = new KakaoUserDTO();
         kakaoUser.setKakaoId(jsonObject.get("id").toString());
         kakaoUser.setEmail(jsonObject.getJSONObject("kakao_account").getString("email"));
         kakaoUser.setNickname(jsonObject.getJSONObject("properties").getString("nickname"));
         
         kakaoUser.setAccessToken(accessToken);

         return kakaoUser;
    	 
    	 
    }
	
}
