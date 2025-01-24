package kr.co.green.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.green.mypage.service.MyPageServiceImpl;
import kr.co.green.mypage.util.PagiNation;

@Controller
@RequestMapping("/duo")
public class MyPageController {

	private final MyPageServiceImpl myPageService;
	private final PagiNation pagiNation;
	
	public MyPageController(MyPageServiceImpl myPageService,
								   PagiNation pagiNation) {
		this.myPageService = myPageService;
		this.pagiNation = pagiNation;
	}
	
	
	@GetMapping("/mypage/home")
	public String myPageHome() {
		String email = myPageService.getUserEmail();
		return "mypage/mypage";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
