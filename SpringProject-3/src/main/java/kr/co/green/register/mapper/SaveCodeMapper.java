package kr.co.green.register.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.green.register.dto.SaveCodeDTO;

@Mapper
public interface SaveCodeMapper {

	int saveCode(SaveCodeDTO saveCodeDTO);
	
	public void saveVerificationCode(SaveCodeDTO saveCodeDTO); // 인증번호 저장
	SaveCodeDTO getVerificationCodeByPhone(String phoneNumber);// 사용자 번호로 최신 인증번호 저장
}
