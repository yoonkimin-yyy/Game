package kr.co.green.kakao.service;

import kr.co.green.kakao.dto.KakaoUserDTO;

public interface KakaoOAuthService {

	// 카카오 액세스 토큰 요청 //
    String getAccessToken(String code);
    
    // 카카오 사용자 정보 요청 //
    KakaoUserDTO getUserInfo(String accessToken);
	
}
