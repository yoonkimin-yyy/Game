package kr.co.green.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.green.file.dto.fileDTO;
import kr.co.green.mypage.dto.MyPageDTO;
import kr.co.green.mypage.service.MyPageServiceImpl;
import kr.co.green.mypage.util.PagiNation;

@Controller
public class FileController {
	
	
	
	

	private final MyPageServiceImpl mypageService;
	private final PagiNation pagination;
	
	public FileController(MyPageServiceImpl mypageService,
								   PagiNation pagination) {
		this.mypageService = mypageService;
		this.pagination = pagination;
	}
	
	
	// 쌤이 알려준 방식
	
	@GetMapping("/enrollForm")
	public String enrollForm(@ModelAttribute("boardDTO") fileDTO fileDTO) {
		return "board/free/enroll";
	}
	
	@PostMapping("/enroll")
	public String enroll(fileDTO fileDTO,
							MyPageDTO mypageDTO,
						 @RequestParam("file") MultipartFile file,
						 @SessionAttribute("memberNo") String memberNo) {
		// boardDTO.setMemberId(memberId);
		// DTO에 memberNo 변수 추가해서 setter로 넣고
		// mapper.xml 까지 쭉 작성		
		fileDTO file1 = new fileDTO();
		
		mypageDTO.setUserEmail(memberNo);
		//fileDTO.getAuthorDTO().setAuthorNo(memberNo);
	
		int result = mypageService.enroll(file,file1);
		
		return "redirect:/board/free/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam(value="no") int no,
						 Model model) {
		// 선택한 게시글에 대한 정보를 불러와야 함
		// 제목, 내용, 작성자, 작성일, 조회수
		fileDTO result = mypageService.detail(no);
		model.addAttribute("post", result);
		return "board/free/detail";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam(value="no") int no,
			 				 Model model) {
		fileDTO result = mypageService.updateForm(no);
		model.addAttribute("post", result);
		return "board/free/update";
	}
	
	@PostMapping("/update")
	public String update(fileDTO fileDTO,
						 @SessionAttribute("memberNo") int memberNo,
						 @RequestParam("file") MultipartFile file,
						 RedirectAttributes redirectAttributes) {
		// 글 작성자만 수정 가능 - 검증이니까 서비스 계층 
		// 1. 게시글 no로 조회해서 글 작성자 no 가져오기
		// 2. 글 작성자 no와 로그인한 사용자의 no(세션에 있는 값)가 같을 경우 update 수행
		int result = mypageService.update(fileDTO, file, memberNo);
		
		// 컨트롤러에서 컨트롤러로 데이터 넘기는 방법
		// 1. redirectAttributes
		//   - 리다이렉트로 데이터 전달할 때 주로 사용
		//   - 일회성 데이터(한번 전달하고 사라짐, 데이터가 유지되지 않음)
		// 2. model-forward 사용
		// 3. GET요청일 경우 쿼리스트링 사용
		// 4. 세션 사용
		redirectAttributes.addAttribute("no", fileDTO.getNo());
//		redirectAttributes.addFlashAttribute("no", boardDTO.getNo());
		return "redirect:/board/free/detail";
	}


// 지피티 방식	
//    // 파일을 저장할 디렉토리 경로 (실제 경로로 변경)
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//    
//    // 업로드 디렉토리가 존재하는지 확인하고, 없으면 생성
//    @PostConstruct
//    public void init() throws IOException {
//        // Path.of()로 경로 객체 생성
//        Path path = Paths.get(uploadDir);
//        
//        // 디렉토리가 없으면 생성
//        if (!Files.exists(path)) {
//            Files.createDirectories(path);
//        }
//   }
//
//  @PostMapping("/upload")
//  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//       if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("파일을 선택해주세요.");
//        }
//
//        try {
//            // 파일을 저장할 경로 설정
//            Path path = Path.of(uploadDir, file.getOriginalFilename());
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//            
//            String imageUrl = "/uploads/" + file.getOriginalFilename();
//            return ResponseEntity.ok("파일 업로드 성공: " + file.getOriginalFilename());
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("파일 업로드 실패: " + e.getMessage());
//        }
//    }
}