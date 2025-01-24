package kr.co.green.register.service;

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
	   
	   System.out.println(loginUser.getUserPassword());
	   
	   
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
 
}	
	
	
	
	
	

