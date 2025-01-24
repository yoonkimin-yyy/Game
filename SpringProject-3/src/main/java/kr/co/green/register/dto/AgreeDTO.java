package kr.co.green.register.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgreeDTO {
	
	private long userNo;
	private String agreeAll; // 전체 동의
	private String essentialAgree; // 필수 약관 동의
	private String personalAgree; //  개인 수집 동의
	private String locationAgree; //  위치 수집 동의 
	private String marketingAgree; // 마케팅 동의
	private String agreeDate; 
	private String personalOption; // 선택 동의
	private String realNameAgree;
}
