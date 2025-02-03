package kr.co.green.profile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfilePageDTO {
	
	private String userProfile; // 유저 프로필(경로)
	private String userNicname; // 유저 닉네임(라이엇)
	private String userIntroduce; // 유저 자기소개
	private String userApplyNicname; // 듀오를 신청한 유저 닉네임
	private String userReview; // 듀오한 유저가 남긴 후기
	
	
}
