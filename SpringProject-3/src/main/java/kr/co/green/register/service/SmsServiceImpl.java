package kr.co.green.register.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class SmsServiceImpl implements SmsService{
	
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
//    	Message message = new Message();
//    	message.setFrom(fromNumber);
//    	message.setTo(phoneNumber);
//    	message.setText("인증번호는 [" + code + "] 입니다.");
//    	
//    	// 메세지 발송
//    	messageService.sendOne(new SingleMessageSendingRequest(message));

    	return code;
    }
    
	@Override
    public String getVerificationCode(String phoneNumber) {
        // 저장된 인증번호 조회
        return verificationCodes.get(phoneNumber);
    }
	
    @Override
    public boolean verifyCode(String phoneNumber, String code) {
        // 저장된 인증번호와 입력된 인증번호 비교
        String storedCode = verificationCodes.get(phoneNumber);
        return storedCode != null && storedCode.equals(code);
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
    
    
    

}
