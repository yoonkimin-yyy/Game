package kr.co.green.register.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.green.register.dto.AgreeDTO;
import kr.co.green.register.dto.RegisterDTO;
import kr.co.green.register.mapper.RegisterMapper;

@Service
public class RegisterServiceImpl  implements RegisterService{

	private final RegisterMapper registerMapper;
	
	private final PasswordEncoder passwordEncoder;

   
    public RegisterServiceImpl(RegisterMapper registerMapper, PasswordEncoder passwordEncoder) {
        this.registerMapper = registerMapper;
        this.passwordEncoder = passwordEncoder;
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
    public int signup(RegisterDTO registerDTO) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(registerDTO.getUserPassword());
        registerDTO.setUserPassword(encodedPassword);

        // 회원가입 처리
        return registerMapper.insertUser(registerDTO);
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
    
}
	
	
	
	
	
	
	
	

