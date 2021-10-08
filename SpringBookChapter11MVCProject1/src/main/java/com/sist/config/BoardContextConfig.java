package com.sist.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// <context:component-scan base-package="com.sist.*"/>
@Configuration
@ComponentScan(basePackages={"com.sist.*"}) // 사용자 정의 => 패키지단위로 등록 
@EnableWebMvc
// HandlerMapping (@RequestMapping,@GetMapping,@PostMapping=> 메모리 할당)
public class BoardContextConfig implements WebMvcConfigurer{
    // 파일이 없는 경우 => 자동으로 404,500,405,400.. 출력리 가능하게 만든다 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
   
}
