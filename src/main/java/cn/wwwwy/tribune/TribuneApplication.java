package cn.wwwwy.tribune;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@MapperScan("cn.wwwwy.tribune.mapper")
@SpringBootApplication
public class TribuneApplication {

	public static void main(String[] args) {
		SpringApplication.run(TribuneApplication.class, args);
	}

}
