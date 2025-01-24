package kr.co.green.mypage.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.green.file.dto.fileDTO;


public interface MyPageService {

	
	public String getUserEmail();
	public int enroll(fileDTO fileDTO,MultipartFile file);
	public fileDTO detail(int no);
	public fileDTO updateForm(int no);
	public int update(fileDTO fileDTO,MultipartFile file, int memberNo);
}
