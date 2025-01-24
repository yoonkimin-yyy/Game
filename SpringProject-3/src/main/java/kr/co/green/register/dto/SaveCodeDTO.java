package kr.co.green.register.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCodeDTO {

	
		private String userPhone;
	 	private String randomNumber;
	    private Date numberDate;
	    private Date expireDate;
	    
}
