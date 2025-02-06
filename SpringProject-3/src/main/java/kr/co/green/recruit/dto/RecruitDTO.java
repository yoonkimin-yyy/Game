package kr.co.green.recruit.dto;

import org.jetbrains.annotations.NotNull;

import kr.co.green.register.dto.RegisterDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecruitDTO {
	 
	private int partyNo;
	private int userNo;
	private String partyContent;
	private String createdDate;
	private String riotName;
	private String riotTag;
	private String lolTier;
	private String lolRank;
	
	private double lolWin;
	
	private double lolLose;
	private String queueType;
	private String myPosition;
	private String findPosition;
	private RegisterDTO registerDTO = new RegisterDTO();
	
	private String profileIconUrl;
}
