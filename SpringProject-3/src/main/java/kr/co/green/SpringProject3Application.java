package kr.co.green;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("kr.co.green.**.mapper")
public class SpringProject3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringProject3Application.class, args);
	}

}
