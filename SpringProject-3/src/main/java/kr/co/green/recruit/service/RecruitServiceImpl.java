package kr.co.green.recruit.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.green.recruit.dto.PageInfoDTO;
import kr.co.green.recruit.dto.RecruitDTO;
import kr.co.green.recruit.mapper.RecruitMapper;
import kr.co.green.recruit.util.PageNation;

@Service
public class RecruitServiceImpl implements RecruitService {
	private final RecruitMapper recruitMapper;
	
    private static final String API_KEY = "RGAPI-0ba3a3c8-7ea3-46f5-95c9-1bb4a4e27ccc"; // Riot API 키
    private static final String REGION = "kr";
	
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
	
	
	// 소환사 아이콘 이미지 코드 시작
	
    
	
	
	
	
	
	
	
	
	
	
	
}
