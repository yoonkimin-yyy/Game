package kr.co.green.kakao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.co.green.kakao.dto.KakaoUserDTO;
import kr.co.green.kakao.service.KakaoOAuthService;

import kr.co.green.kakao.service.UserServiceImpl;

@Controller
public class KakaoOAuthController {

	@Value("${kakao.client_id}")
	private String kakaoClientId;

	 @Value("${kakao.redirect_uri}")
	 private String kakaoRedirectUri;
	 
	
	private final KakaoOAuthService kakaoOAuthService;
	
	private final UserServiceImpl userService;
	
	public KakaoOAuthController(KakaoOAuthService kakaoOAuthService, UserServiceImpl userService) {
		this.kakaoOAuthService = kakaoOAuthService;
		this.userService = userService;
		
	}
	
	//카카오 회원가입 버튼 클릭시 요청//
	@GetMapping("/oauth/kakao")
	public String kakaoLogin(Model model) {
		
		String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="
                + kakaoClientId + "&redirect_uri=" + kakaoRedirectUri;
		
		
		return "redirect:"+ kakaoAuthUrl;
		
	}
	// 2️ 카카오 로그인 후 콜백 (카카오 회원가입 처리) //
	
	@GetMapping("/kakao/callback")
	public String kakaoCallback(@RequestParam("code") String code,HttpSession session) {
		String accessToken = kakaoOAuthService.getAccessToken(code);
		KakaoUserDTO kakaoUser = kakaoOAuthService.getUserInfo(accessToken);
		
		
		// 기존 회원인지 확인
		System.out.println(userService.findKakaoId(kakaoUser.getKakaoId()));
		if(!userService.findKakaoId(kakaoUser.getKakaoId())) {
			// 신규 회원이면 회원가입 진행
			userService.signupKakao(kakaoUser);
			
		}
		// session에 사용자 정보 저장
		session.setAttribute("userId", kakaoUser.getKakaoId());
		session.setAttribute("email", kakaoUser.getEmail());
		session.setAttribute("nickName", kakaoUser.getNickname());
		
		return "redirect:/MatchMyduo/home";
		
	}
	
	
	
}
