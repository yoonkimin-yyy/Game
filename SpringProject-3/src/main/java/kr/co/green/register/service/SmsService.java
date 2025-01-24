package kr.co.green.register.service;

public interface SmsService {

	String sendCertificationCode(String phoneNumber); // SMS 인증번호 발송

    String getVerificationCode(String phoneNumber); // 인증번호 조회

    boolean verifyCode(String phoneNumber, int userInputNumber); // 인증번호 검증
	
	
}
