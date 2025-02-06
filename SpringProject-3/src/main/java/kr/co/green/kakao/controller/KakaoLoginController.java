package kr.co.green.kakao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.co.green.kakao.dto.KakaoUserDTO;
import kr.co.green.kakao.service.KakaoOAuthService;
import kr.co.green.kakao.service.UserService;

@Controller
public class KakaoLoginController {

	private final KakaoOAuthService kakaoOAuthService;
	private final UserService userService;
	
	public KakaoLoginController(KakaoOAuthService kakaoOAuthService,UserService userService) {
		this.kakaoOAuthService = kakaoOAuthService;
		this.userService = userService;
	}
	
	//로그인 처리
	@GetMapping("/kakao/login/callback")
	public String kakaoLogin(@RequestParam("code") String code, HttpSession session) {
		
		String accessToken = kakaoOAuthService.getAccessToken(code);
		KakaoUserDTO kakaoUser = kakaoOAuthService.getUserInfo(accessToken);
		
		// 기존 회원 여부확인
		if(!userService.findKakaoId(kakaoUser.getKakaoId())) {
			userService.signupKakao(kakaoUser);
		}
		
		// 로그인시 회원 정보 조회
		KakaoUserDTO loginUser = userService.signIn(kakaoUser.getKakaoId());
		
		
		
		//session에 로그인 정보 저장
		session.setAttribute("userId", kakaoUser.getKakaoId());
		session.setAttribute("email", kakaoUser.getEmail());
		session.setAttribute("nickName", kakaoUser.getNickname());
		
		return "redirect:/MatchMyduo/home";
	}
	
	
	// 로그아웃 처리
	@GetMapping("/kakao/logout")
	public String kakaoLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
}
