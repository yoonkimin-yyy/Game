package kr.co.green.recruit.service;

import java.util.Map;

import kr.co.green.recruit.dto.RecruitDTO;
import kr.co.green.recruit.util.PageNation;

public interface RecruitService {

	public int partyEnroll(RecruitDTO recruitDTO);
	
	public int totalCount();
	
	public Map<String,Object> recruit(PageNation pageNation,
									  int currentPage,
									  int postCount,
									  int pageLimit,
									  int boardLimit);	
}
