package com.sist.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Before;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// dispatcher-servlet.xml을 자바로 대체
/*
 *   @Component, 
 *   @Repository, 
 *   @Service,  (X) => DAO
 *   @Controller,  
 *   @RestController, (X) => 16장
 *   @ControllerAdvice, (X) 
     @Configuration
 */
@Configuration // 클래스 등록 파일이다 => 자동 메모리 할당이 된다 
// <context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages={"com.sist.*"})
@EnableWebMvc //HandlerMapping,HandlerAdapter => 클래스 로딩 (254page)
public class MusicConfig implements WebMvcConfigurer{

	//jsp를 찾기에 실패했을 경우에 자동으로 404,500 => 처리 파일을 브라우저 전송할 수 있게 만드는 기능 (include)
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	// 클래스 등록 
	/*
		 *   <bean id="ds"
	        class="org.apache.commons.dbcp.BasicDataSource"
	        p:driverClassName="oracle.jdbc.driver.OracleDriver"
	        p:url="jdbc:oracle:thin:@211.238.142.181:1521:xe"
	        p:username="hr"
	        p:password="happy"
	     />
	     <!-- getConnection/disConnection : SqlSessionFactoryBean -->
	     <bean id="ssf"
	         class="org.mybatis.spring.SqlSessionFactoryBean"
	         p:dataSource-ref="ds"
	     />
	     <!-- interface 구현 : MapperFactoryBean -->
	     <bean class="org.mybatis.spring.mapper.MapperFactoryBean"
	         p:sqlSessionFactory-ref="ssf"
	         p:mapperInterface="com.sist.dao.MusicMapper"
	     />
	     <!-- JSP를 찾기 시작 : ViewResolver (JSP경로명 , 확장자) -->
	     <bean id="viewResolver"
	          class="org.springframework.web.servlet.view.InternalResourceViewResolver"
	          p:prefix="/"
	          p:suffix=".jsp"
	     />
	 */
	@Bean("ds") // id설정 : bean id="ds"
	public BasicDataSource basicDataSource()
	{
		// 메모리 할당  class="org.apache.commons.dbcp.BasicDataSource"
		BasicDataSource ds=new BasicDataSource();
		/*
		 *   p:driverClassName="oracle.jdbc.driver.OracleDriver"
	        p:url="jdbc:oracle:thin:@211.238.142.181:1521:xe"
	        p:username="hr"
	        p:password="happy"
		 */
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@211.238.142.181:1521:xe");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	
	@Bean("ssf") // id="ssf"
	public SqlSessionFactory sqlSessionFactory() throws Exception
	{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		//class="org.mybatis.spring.SqlSessionFactoryBean"
		// p:dataSource-ref="ds" => 클래스객체 첨부 
		ssf.setDataSource(basicDataSource());
		return ssf.getObject();
	}
	
	@Bean //ID가 없는 경우
	/*
	 *   <bean class="org.mybatis.spring.mapper.MapperFactoryBean"
	         p:sqlSessionFactory-ref="ssf"
	         p:mapperInterface="com.sist.dao.MusicMapper"
	     />
	 */
	public MapperFactoryBean mapperFactoryBean() throws Exception
	{
		MapperFactoryBean mapper=new MapperFactoryBean();
		mapper.setSqlSessionFactory(sqlSessionFactory());
		mapper.setMapperInterface(com.sist.dao.MusicMapper.class);
		return mapper;
	}
	/*
	 *   <bean id="viewResolver"
	          class="org.springframework.web.servlet.view.InternalResourceViewResolver"
	          p:prefix="/"
	          p:suffix=".jsp"
	     />
	 */
	@Bean("viewResolver") //id="viewResolver"
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver r=new InternalResourceViewResolver();
		r.setPrefix("/");
		r.setSuffix(".jsp");
		return r;
	}
    
}











