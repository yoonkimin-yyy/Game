package kr.co.green.profile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.green.mypage.util.PagiNation;
import kr.co.green.profile.service.ProfilePageServiceImpl;

@Controller
@RequestMapping("/duo")
public class ProfilePageController {

	
	private final ProfilePageServiceImpl profilePageService;
	private final PagiNation pagiNation;
	
	
	public ProfilePageController(ProfilePageServiceImpl profilePageService,
					   PagiNation pagiNation) {
		this.profilePageService = profilePageService;
		this.pagiNation = pagiNation;
		}
		
		
		@GetMapping("/profilepage/home")
		public String myPageHome() {
		String email = profilePageService.getUserEmail();
		return "profilepage/profilepage.html";
		}
}
