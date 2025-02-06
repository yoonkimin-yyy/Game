package kr.co.green.kakao.service;

import kr.co.green.kakao.dto.KakaoUserDTO;

public interface UserService {

	//  카카오 ID 존재 여부 확인 //
    boolean findKakaoId(String kakaoId);
    
    //  카카오 회원가입 //
    void signupKakao(KakaoUserDTO kakaoUser);

    // 카카오로 로그인
    KakaoUserDTO signIn(String kakaoId);
   
}
