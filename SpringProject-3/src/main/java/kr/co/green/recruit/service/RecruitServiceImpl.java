package kr.co.green.recruit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.green.recruit.dto.PageInfoDTO;
import kr.co.green.recruit.dto.RecruitDTO;
import kr.co.green.recruit.mapper.RecruitMapper;
import kr.co.green.recruit.util.PageNation;

@Service
public class RecruitServiceImpl implements RecruitService {
	private final RecruitMapper recruitMapper;
	
	public RecruitServiceImpl(RecruitMapper recruitMapper) {
		this.recruitMapper = recruitMapper;
	}
	
	@Override
	public int partyEnroll(RecruitDTO recruitDTO) {
		int result = recruitMapper.partyEnroll(recruitDTO);
		return result;
	}
	
	@Override
	public int totalCount() {
		return recruitMapper.totalCount();
	}
	
	@Override
	public Map<String,Object> recruit(PageNation pageNation,
									 int currentPage,
									 int postCount,
									 int pageLimit,
									 int boardLimit){
		PageInfoDTO pi = pageNation.getPageInfo(postCount, currentPage, pageLimit, boardLimit);
		
		List<RecruitDTO> posts = recruitMapper.recruit(pi); 
		
		Map<String,Object> result = new HashMap<>();
		result.put("pi", pi);
		result.put("posts", posts);
		
		return result;
	}
}
