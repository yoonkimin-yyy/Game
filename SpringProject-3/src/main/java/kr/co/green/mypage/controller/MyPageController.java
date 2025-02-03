package kr.co.green.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.green.file.dto.fileDTO;
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
	public String myPageHome(@SessionAttribute(value="userNo") int no,
			 Model model) {
		System.out.println(no);
		String email = myPageService.getUserEmail();
		// 선택한 게시글에 대한 정보를 불러와야 함
		// 제목, 내용, 작성자, 작성일, 조회수
		fileDTO result = myPageService.detail(no);
		model.addAttribute("post", result);
		
		return "mypage/mypage";
	}
	@GetMapping("/detail")
	public String detail(@RequestParam(value="no") int no,
						 Model model) {
		// 선택한 게시글에 대한 정보를 불러와야 함
		// 제목, 내용, 작성자, 작성일, 조회수
		fileDTO result = myPageService.detail(no);
		model.addAttribute("post", result);
		return "board/free/detail";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
