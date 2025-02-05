package kr.co.green.mypage.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.green.file.dto.fileDTO;


public interface MyPageService {

	
	public String getUserEmail();
	public fileDTO detail(int no);
}
