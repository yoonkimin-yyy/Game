
package kr.co.green.register.service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.green.register.dto.AgreeDTO;
import kr.co.green.register.dto.RegisterDTO;
import kr.co.green.register.dto.SaveCodeDTO;
import kr.co.green.register.mapper.RegisterMapper;
import kr.co.green.register.mapper.SaveCodeMapper;

@Service
public class RegisterServiceImpl  implements RegisterService{

	private final RegisterMapper registerMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	private final SaveCodeMapper saveCodeMapper;

   
    public RegisterServiceImpl(RegisterMapper registerMapper, PasswordEncoder passwordEncoder,SaveCodeMapper saveCodeMapper) {
        this.registerMapper = registerMapper;
        this.passwordEncoder = passwordEncoder;
        this.saveCodeMapper = saveCodeMapper;
    }

    @Override
    public boolean checkId(String userId) {
    	
    	
        int result = registerMapper.checkId(userId); // DB에서 아이디 중복 체크
        return result > 0; // 중복된 아이디가 있으면 true 반환
    }
    @Override
    public boolean checkEmail(String userEmail) {
    	
    	int result = registerMapper.checkEmail(userEmail);
    	return result > 0;
    }

    @Override
    public int signup(RegisterDTO registerDTO,AgreeDTO agreeDTO) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(registerDTO.getUserPassword());
        registerDTO.setUserPassword(encodedPassword);

        // 회원가입 처리
        registerMapper.insertUser(registerDTO);
        
        agreeDTO.setUserNo(registerDTO.getUserNo());
        
        return registerMapper.insertCheckDate(agreeDTO);
        
    }
   @Override
   public RegisterDTO signIn(RegisterDTO registerDTO) {
	   
	   
	   RegisterDTO loginUser = registerMapper.signIn(registerDTO);
	   
	  
	   
	   
	   if(passwordEncoder.matches(registerDTO.getUserPassword(),loginUser.getUserPassword())) {
		   return loginUser;
	   }else {
		   return null;
	   }
	    
   }
    @Override
    public boolean processAgree(AgreeDTO agreeDTO) {
    	try {
    		registerMapper.insertAgree(agreeDTO);
    		return true;
    	} catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    @Override
    public void saveVerificationCode(SaveCodeDTO saveCodeDTO) {
    	saveCodeMapper.saveVerificationCode(saveCodeDTO);
    }
    
    @Override
    public String  findUserId(String userEmail) {
    	System.out.println(userEmail);
    	
    	String useEmail = registerMapper.findUserId(userEmail);
    	
    	System.out.println(useEmail);
    	
    	if(useEmail == null) {
    		return null;
    	}else {
    		return maskUserId(useEmail);
    	}
    }
    private String maskUserId(String userId) {
    	if(userId.length() <= 3)return "***";
    	return userId.substring(0,3) + "***";
    }
    @Override
    public String generateAndSaveTempPassword(String userEmail) {
    	// 임시 비밀 번호 생성 랜덤한
    	String tempPassword = generateRandomPassword(10);
    	
    	// 비밀번호 암호화후 db에 업데이트
    	String encryptedPassword = passwordEncoder.encode(tempPassword);
    	registerMapper.updateUserPassword(userEmail, encryptedPassword);
    	
    	return tempPassword;
    	
    }
    
    
    
    private String generateRandomPassword(int length) {
    	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        
        for(int i=0; i < length; i++) {
        	password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
}	
	
	
	
	
	

