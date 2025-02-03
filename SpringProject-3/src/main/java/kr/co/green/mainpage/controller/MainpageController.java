package kr.co.green.mainpage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/MatchMyduo")
public class MainpageController {

	
	@GetMapping("/home")
	public String home() {
		return "mainpage/mainpage";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/MatchMyduo/home";
	}
}
