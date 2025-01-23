package kr.co.green.register.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCodeDTO {

	
		private long userNo;
	 	private int randomNumber;
	    private String numberDate;
	    private String expireDate;
}
