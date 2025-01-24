package kr.co.green.mypage.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.green.file.dto.fileDTO;

@Mapper
public interface MyPageMapper {

	int enroll(fileDTO boardDTO);

	fileDTO detail(int no);


	fileDTO updateForm(int no);

	int update(fileDTO boardDTO);

	
	
	int enrollFile(fileDTO boardDTO);
	
	fileDTO getFileInfo(int no);
}
