package kr.co.green.kakao.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoUserDTO {

	private int socialUserNo;  //  SOCIAL_USER_NO (고유번호)
    private int  userNo;     // user_no(user_info테이블과 연결)
	private String kakaoId;
    private String email;
    private String nickname;
    private String socialProfile;  //  SOCIAL_PROFILE (카카오 프로필 이미지)
    private String socialType = "KAKAO";  //  SOCIAL_TYPE (기본값 'KAKAO')
    private String createDate;  //  CREATE_DATE (가입 날짜)
    private String accessToken; // 카카오 토큰
	
}
