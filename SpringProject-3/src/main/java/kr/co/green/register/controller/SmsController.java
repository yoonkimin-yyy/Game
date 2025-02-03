package kr.co.green.register.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.green.register.service.SmsService;

@RestController
@RequestMapping("/sms")
public class SmsController {
	
	private final SmsService smsService;
	
	public SmsController(SmsService smsService) {
		this.smsService = smsService;
	}
	
	@PostMapping("/send")
	public String sendSms(@RequestParam String phoneNumber) {
		smsService.sendCertificationCode(phoneNumber);
		return "인증번호가 발송되었습니다.";
	}
	@PostMapping("/verify")
	public boolean verifySms(@RequestParam String phoneNumber, int userInputNumber) {
		return smsService.verifyCode(phoneNumber,  userInputNumber);
	}
	
	
	
}
