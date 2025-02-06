package kr.co.green.kakao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.green.kakao.dto.KakaoUserDTO;
import kr.co.green.register.dto.RegisterDTO;

@Mapper
public interface UserMapper {

	// 카카오 회원 정보 저장 //
    void insertKakaoUser(KakaoUserDTO kakaoUser);

    //  카카오 ID 존재 여부 확인 //
    Integer countByKakaoId(@Param("kakaoId") String kakaoId);
    
    // USER_INFO에 사용자 추가
    void insertUser(RegisterDTO registerDTO);

    KakaoUserDTO loginKakaoId(@Param("kakaoId") String kakaoId);
}
