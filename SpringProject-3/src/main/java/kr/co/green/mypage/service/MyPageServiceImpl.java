package kr.co.green.mypage.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.green.file.dto.fileDTO;
import kr.co.green.mypage.mapper.MyPageMapper;
import kr.co.green.mypage.util.FileUpload;

@Service
public class MyPageServiceImpl implements MyPageService{

	
	private final FileUpload fileUpload;
	private final MyPageMapper mypageMapper;
	
	
	
	@Override
	public String getUserEmail() {
		return null;
	}




	
	
	
	
	
}
