package kr.co.green.register.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.green.register.dto.AgreeDTO;
import kr.co.green.register.dto.RegisterDTO;

@Mapper
public interface RegisterMapper {

	
	public int checkId(String userId); // 아이디 중복체크
	public int signup(RegisterDTO registerDTO); // 회원가입 처리
	public int checkEmail(String userEmail); // 이메일 중복체크
	public int insertUser(RegisterDTO registerDTO);
	public int insertCheckDate(AgreeDTO agreeDTO);
	public RegisterDTO signIn(RegisterDTO registerDTO);
	void insertAgree(AgreeDTO agreeDTO);
}

