package kr.co.green.register.service;

import kr.co.green.register.dto.AgreeDTO;
import kr.co.green.register.dto.RegisterDTO;
import kr.co.green.register.dto.SaveCodeDTO;

public interface RegisterService {

	boolean checkId(String userId); // 아이디 중복체크
	public int signup(RegisterDTO registerDTO,AgreeDTO agreeDTO); // 회원가입 처리
	boolean checkEmail(String userEmail); // 이메일 중복체크
	RegisterDTO signIn(RegisterDTO registerDTO);
	boolean processAgree(AgreeDTO agreeDTO);
	
	
	
	public void saveVerificationCode(SaveCodeDTO saveCodeDTO); //인증번호 저장
	
	
}
