
package kr.co.green.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.co.green.register.dto.AgreeDTO;
import kr.co.green.register.dto.RegisterDTO;
import kr.co.green.register.mapper.SaveCodeMapper;
import kr.co.green.register.service.RegisterServiceImpl;
import kr.co.green.register.service.SmsService;



@Controller
@RequestMapping("/MatchMyduo")
public class RegisterController {

	private final RegisterServiceImpl registerService;
	private final SmsService smsService;
	private final SaveCodeMapper saveCodeMapper;

	
	public RegisterController(RegisterServiceImpl registerService,SmsService smsService,SaveCodeMapper saveCodeMapper) {
		this.registerService = registerService;
		this.smsService = smsService;
		this.saveCodeMapper = saveCodeMapper;
	}
	
	
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute RegisterDTO registerDTO, @RequestParam("userInputCode") int userInputCode,
			AgreeDTO agreeDTO) {
		
		
		String phoneNumber = registerDTO.getUserPhone();
		
		boolean isVerified = smsService.verifyCode(phoneNumber, userInputCode);
		
		if(!isVerified) {
			return "MatchMyduo/signup";
		}
		
		// 회원가입 요청 처리
		int result = registerService.signup(registerDTO,agreeDTO);
		System.out.println(registerDTO.getNickName());
		
		if(result > 0) {
			return "register/signin";
		}else {
			return "/register/signup";
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
	
	@PostMapping("/register/form")
	public String register() {
		
		return "register/signup";
	}
	
	@GetMapping("register/signin")
	public String singinForm() {
		return "register/signin";
	}
	// 로그인
	@PostMapping("/signin")
	public String signIn(RegisterDTO registerDTO, HttpSession session ) {
		RegisterDTO loginUser = registerService.signIn(registerDTO);
		if(loginUser != null) {
			session.setAttribute("userNo", loginUser.getUserNo());
			session.setAttribute("userId", loginUser.getUserId());
			session.setAttribute("userEmail", loginUser.getUserEmail());
			
		
			return  "redirect:/MatchMyduo/home";
		}
		
		return "/register/signup";
	}
	
	@GetMapping("/register/agree")
	public String agreeuser() {
		
		return "/register/agree";
	}
	
	@PostMapping("/register/agree")
	public String agree(AgreeDTO agreeDTO, Model model){
		
		model.addAttribute("agreeDTO", agreeDTO);
		
	
		return "register/signup";
		
	}
	@GetMapping("/findId")
	public String findId() {
		return "register/findId";
	}
	// 아이디 찾기
	@PostMapping("/findOfEmail")
	public String findOfEmail(@RequestParam("userEmail") String userEmail,Model model) {
		
		
		String foundUserId = registerService.findUserId(userEmail);
		System.out.println("email : "+foundUserId);
		
		
		
		if(foundUserId == null) {
			model.addAttribute("error", "해당 이메일로 가입된 아이다가 없습니다.");
		}else {
			model.addAttribute("userId", foundUserId);
		}
		return "register/findId";
	}
	@GetMapping("/findPassword")
	public String findPassword() {
		return "register/findPassword";
	}
	@PostMapping("/giveNewPassword")
	public String giveNewPassword(@RequestParam("userEmail")String userEmail, Model model) {
		System.out.println(userEmail);
		boolean emailExists = registerService.checkEmail(userEmail);
		
		if(!emailExists) {
			model.addAttribute("error", "해당 이메일로 가입된 계정이 없습니다.");
			return "register/findPassword";
		}
		
		// 임시 비번 생성 및 저장
		String tempPassword = registerService.generateAndSaveTempPassword(userEmail);
		System.out.println(tempPassword);
		
		model.addAttribute("success", "임시 비밀번호 : " + tempPassword);
		
		return "register/findPassword";
	}
	
	
}
