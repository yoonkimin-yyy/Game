package kr.co.green.file.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class fileDTO {
	private final String LOCAL_PATH = "C:\\dev\\spring_work_space\\SpringProject-3\\src\\main\\resources\\static\\uploads";
	private final String RESOURCES_PATH = "/uploads";
	
	private int no;
	private String changeName;
	private String originalName;
	private String uploadDate;
	private String folderNamePath;
	
	private String extension;
	private long size;
}
