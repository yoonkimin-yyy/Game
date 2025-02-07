package kr.co.green.register.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import kr.co.green.register.dto.SaveCodeDTO;
import kr.co.green.register.mapper.SaveCodeMapper;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class SmsServiceImpl implements SmsService{
	
	private final SaveCodeMapper saveCodeMapper;
	
	public  SmsServiceImpl(SaveCodeMapper saveCodeMapper) {
		this.saveCodeMapper = saveCodeMapper;
	}
	
	@Value("${coolsms.apikey}")
    private String apiKey;

    @Value("${coolsms.apisecret}")
    private String apiSecret;

    @Value("${coolsms.fromnumber}")
    private String fromNumber;

    private DefaultMessageService messageService;
    
    //인증번호 저장소
    private final Map<String, String> verificationCodes = new HashMap<>();
    
    @PostConstruct
    public void init() {
    	// coolsms 서비스 초기화
    	this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }
    @Override
    public String sendCertificationCode(String phoneNumber) {
    	System.out.println(apiKey);
    	System.out.println(apiSecret);
    	// 6자리 랜덤 인증번호 생성
    	String code =  generateRandomCode(6);
    	// sms 메세지 작성
    	Message message = new Message();
    	message.setFrom(fromNumber);
    	message.setTo(phoneNumber);
    	message.setText("인증번호는 [" + code + "] 입니다.");
    	
    	// 메세지 발송
    	messageService.sendOne(new SingleMessageSendingRequest(message));

    	SaveCodeDTO saveCode = new SaveCodeDTO();
    	
    	saveCode.setRandomNumber(code);
    	saveCode.setUserPhone(phoneNumber);
    	
    	saveCodeMapper.saveVerificationCode(saveCode);
    	
    	
    	return code;
    }
    @Override
    public boolean verifyCode(String phoneNumber, int userInputCode) {
    	SaveCodeDTO saveCode = saveCodeMapper.getVerificationCodeByPhone(phoneNumber);
    	
    	System.out.println(saveCode.getRandomNumber());
    	System.out.println(phoneNumber);
    	if(saveCode == null) {
    		return false;
    	}
    	
    	// 현재 시간
    	Date currentTime = new Date();
    	
    	Date expireDate = saveCode.getExpireDate();
    	
    	// 현재시간이랑 만료시간 비교
    	if(expireDate != null && currentTime.after(expireDate)) {
    		
    		return false;
    	}
    	
    	String randomCode = saveCode.getRandomNumber();
    	
    	String userCode = String.valueOf(userInputCode);
    	
    	return randomCode.equals(String.valueOf(userCode));
    	
    }
    private String generateRandomCode(int length) {
    	// 인증번호 생성(랜덤숫자6)
    	Random random = new Random();
    	StringBuilder code = new StringBuilder();
    	for(int i = 0; i<length; i++) {
    		code.append(random.nextInt(10));
    	}
    	return code.toString();
    }
    
    @Override
    public String getVerificationCode(String phoneNumber) {
    	SaveCodeDTO saveCode = saveCodeMapper.getVerificationCodeByPhone(phoneNumber);
        
    	//조회결과 없으면 NULL반환이나 예외처리
    	if (saveCode == null) {
            return null;
            
        }
    	//DB에 문자열로 저장된 랜덤인증 코드 반환
    	return saveCode.getRandomNumber();
    	
    }

}
