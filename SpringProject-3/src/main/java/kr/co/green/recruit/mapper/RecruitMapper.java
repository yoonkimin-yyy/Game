package kr.co.green.recruit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.green.recruit.dto.PageInfoDTO;
import kr.co.green.recruit.dto.RecruitDTO;

@Mapper
public interface RecruitMapper {
	public int partyEnroll(RecruitDTO recruitDTO);
	
	public int totalCount();
	
	public List<RecruitDTO> recruit(@Param("pi")PageInfoDTO pi);
}
