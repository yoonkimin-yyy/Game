package kr.co.green.register.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.co.green.register.dto.SaveCodeDTO;

@Mapper
public interface SaveCodeMapper {

	int saveCode(SaveCodeDTO saveCodeDTO);
	
	SaveCodeDTO findCode(long userNo);
	
}
