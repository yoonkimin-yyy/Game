package kr.co.green.register.controller;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.co.green.register.dto.AgreeDTO;
import kr.co.green.register.dto.RegisterDTO;
import kr.co.green.register.dto.SaveCodeDTO;
import kr.co.green.register.mapper.SaveCodeMapper;
import kr.co.green.register.service.RegisterServiceImpl;
import kr.co.green.register.service.SmsService;



@Controller
@RequestMapping("/register")
public class RegisterController {

	private final RegisterServiceImpl registerService;
	private final SmsService smsService;
	private final SaveCodeMapper saveCodeMapper;

	
	public RegisterController(RegisterServiceImpl registerService,SmsService smsService,SaveCodeMapper saveCodeMapper) {
		this.registerService = registerService;
		this.smsService = smsService;
		this.saveCodeMapper = saveCodeMapper;
	}
	@GetMapping("/home")
	public String home() {
		return "register/home";
	}
	
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute RegisterDTO registerDTO, 
					@RequestParam String verificationCode
					) {
		// 1. 인증발송 버튼을 누르면 SmsServiceImpl가 실행
		// 2. Mapper랑 테이블까지 쭉 만들어서 인증 번호를 저장
		//    -> 생성된 랜덤 숫자(6자), 회원에 대한 정보(no), 생성 날짜, 유효 날짜
		
		// 3. 자바스크립트에서 인증 번호가 틀리면 '회원가입 버튼 안눌려야 함' 맞으면 회원가입 요청(사용자가 입력한 인증 코드도 전송)
		// 4. DB에서 저장했던 랜덤 숫자를 꺼내오고, 사용자가 입력한 인증 코드랑 일치하는지 확인
		// 5. 일치하면 회원가입 진행, 실패하면 ~~
		
		//1 인증번호 생성및 전송
		String code = smsService.sendCertificationCode(registerDTO.getUserPhone());
		//2 랜덤 인증 번호 저장
		SaveCodeDTO saveCode = new SaveCodeDTO();
		saveCode.setRandomNumber(Integer.parseInt(code));
		saveCode.setNumberDate(new Date().toString());
		saveCode.setExpireDate(new Date(System.currentTimeMillis()+1000).toString());
		saveCode.setUserNo(registerDTO.getUserNo());
		saveCodeMapper.saveCode(saveCode);
		
		
		// 인증번호 검증
		boolean isVerified = smsService.verifyCode(registerDTO.getUserPhone(), verificationCode);
	    if (!isVerified) {
	        return "redirect:/register/signup?error=verificationFailed";
	    }
		// 회원가입 요청 처리
		int result = registerService.signup(registerDTO);
		System.out.println(registerDTO.getNickName());
		
		if(result > 0) {
			return "redirect/signin/form";
		}else {
			return "redirect/register/signup";
		}
		
		
	}
	// 아이디 중복검사!!!!!!!
	@GetMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestParam("userId") String userId) {
		
		boolean isDuplication = registerService.checkId(userId);
		
		 return isDuplication ? "true" : "false";
	}
	// 이메일 중복검사!!!!!
	@GetMapping("/checkEmail")
	@ResponseBody
	public String checkEmail(@RequestParam("userEmail") String userEmail) {
		
		boolean isDuplication = registerService.checkEmail(userEmail);
		
		return isDuplication ? "true" : "false";
	}
	// 인증번호 발송
	@PostMapping("/sendSms")
	@ResponseBody
	public String sendSms(@RequestParam("phoneNumber") String phoneNumber) {
		String result = smsService.sendCertificationCode(phoneNumber);
		
		// /templates/인증번호가 발송되었습니다..html
		return result;
		
	}
	
	@PostMapping("register/form")
	public String register() {
		
		return "register/signup";
	}
	
	@GetMapping("register/signin")
	public String singin() {
		return "register/signin";
	}
	// 로그인
	@PostMapping("/signin")
	public String signIn(RegisterDTO registerDTO, HttpSession session ) {
	
		
		
		RegisterDTO loginUser = registerService.signIn(registerDTO);
		
		
		
		if(loginUser != null) {
			
			session.setAttribute("userId", loginUser.getUserId());
			session.setAttribute("userEmail", loginUser.getUserEmail());
			
		
			return "redirect:/register/home";
		}
		
		return "/register/signup";
	}
	
	@GetMapping("/register/agree")
	public String agreeuser() {
		
		return "/register/agree";
	}
	
	@PostMapping("/register/agree")
	public ResponseEntity<String> agree(@RequestBody AgreeDTO agreeDTO){
		boolean success = registerService.processAgree(agreeDTO);
		if(success) {
			return ResponseEntity.ok("Agreement successfully procesed");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process agreement");
					
		}
	}
	
	
}
