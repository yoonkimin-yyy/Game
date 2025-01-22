package kr.co.green.mainpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.green.main.service.MainPageServiceImpl;
import kr.co.green.mypage.service.MyPageServiceImpl;
import kr.co.green.mypage.util.PagiNation;

@Controller
@RequestMapping("/duo")
public class MainPageController {

	
	
	private final MainPageServiceImpl mainPageService;
	private final PagiNation pagiNation;
	
	public MainPageController(MainPageServiceImpl mainPageService,
								   PagiNation pagiNation) {
		this.mainPageService = mainPageService;
		this.pagiNation = pagiNation;
	}
	
	
	@GetMapping("/mainpage/home")
	public String myPageHome() {
		String email = mainPageService.getUserEmail();
		return "mainpage/mainpage.html";
	}
}
