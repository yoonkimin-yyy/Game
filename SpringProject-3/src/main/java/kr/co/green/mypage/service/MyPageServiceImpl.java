package kr.co.green.mypage.service;

import org.springframework.stereotype.Service;

import kr.co.green.file.dto.fileDTO;
import kr.co.green.profile.mapper.ProfilePageMapper;

@Service
public class MyPageServiceImpl implements MyPageService{

	
	
	
	
	
	@Override
	public String getUserEmail() {
		return "";
	}
	@Override
	public fileDTO detail(int no) {
		
		
		
			// no가지고 file 테이블에 데이터가 있는지
			// 데이터가 있으면 boardDTO.fileDTO에 넣고
			fileDTO fileCheck = ProfilePageMapper.getFileInfo(no);
			fileDTO result;
			
			
			if(fileCheck != null) {
				fileCheck.setNo(no);
				result = ProfilePageMapper.detailFile(fileCheck);
				result.setFileDTO(fileCheck.getFileDTO());
			}else {
				result = ProfilePageMapper.detail(no);
			}
			
			
			// free_board 테이블 다시 SELECT해서 제목, 내용 가져오기
			return result;
	}




	
	
	
	
	
}
