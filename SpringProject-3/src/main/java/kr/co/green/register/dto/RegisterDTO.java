package kr.co.green.register.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDTO {

	
	
	private Long userNo;           // USER_NO: 사용자 고유 번호
    private String userName;       // USER_NAME: 사용자 이름
    private String nickName;       // NICK_NAME: 닉네임
    private String userId;         // USER_ID: 사용자 ID
    private String userPassword;   // USER_PASSWORD: 사용자 비밀번호 (암호화 예정)
    private String userEmail;      // USER_EMAIL: 이메일
    private String userBirthday;   // USER_BIRTHDAY: 생년월일
    private String userAgency;     // USER_AGENCY: 통신사 (SKT, LG, KT, ANY)
    private String userPhone;      // USER_PHONE: 전화번호
    private LocalDate createDate;  // CREATE_DATE: 계정 생성일
    private String roleEnum;       // ROLE_ENUM: 사용자 역할 (기본값 USER)
    private String userIntroduce;  // USER_INTRODUCE: 자기소개
    private String userRiot;       // USER_RIOT: Riot 계정 연동 여부 (기본값 NO)
	
	
	
	
}
