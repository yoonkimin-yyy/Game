package kr.co.green.kakao.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.green.kakao.dto.KakaoUserDTO;
import kr.co.green.kakao.mapper.UserMapper;
import kr.co.green.register.dto.RegisterDTO;

@Service
public class UserServiceImpl implements UserService {

	private final 	UserMapper userMapper;
	
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	
	@Override
	public boolean findKakaoId(String kakaoId) {
		
		return Optional.ofNullable(userMapper.countByKakaoId(kakaoId)).orElse(0) > 0;
}
	//(USER_INFO + SOCIAL_USER_INFO 저장)
	@Transactional
	@Override
	public void signupKakao(KakaoUserDTO kakaoUser) {
		
		// 카카오 아이디 중복확인
		if(findKakaoId(kakaoUser.getKakaoId())) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
		
		
		//USER_INFO에 사용자 정보 추가 (USER_NO 생성)
		RegisterDTO userInfo = new RegisterDTO();
		userInfo.setNickName(kakaoUser.getNickname());
		userInfo.setUserEmail(kakaoUser.getEmail());
		userInfo.setKakaoId(kakaoUser.getKakaoId());
		
		System.out.println(userInfo.getKakaoId());
		System.out.println(userInfo.getNickName());
		System.out.println(userInfo.getKakaoId());
		
		userMapper.insertUser(userInfo);
		Integer userNo = userInfo.getUserNo();
		
		// SOCIAL_USER_INFO에 소셜 로그인 정보 추가 (USER_NO 활용)
		kakaoUser.setSocialUserNo(userInfo.getUserNo());
		kakaoUser.setUserNo(userNo);
		userMapper.insertKakaoUser(kakaoUser);
	
	}
	
	
	
	
}
