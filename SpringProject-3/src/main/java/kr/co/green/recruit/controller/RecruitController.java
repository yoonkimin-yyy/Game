package kr.co.green.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MatchMyduo")
public class RecruitController {

	// RGAPI-faa24592-2f88-459c-91b5-a2c1fb607fad
	
	@GetMapping("/recruit")
	public String recruit() {
		return "recruit/recruit";
	}
}
