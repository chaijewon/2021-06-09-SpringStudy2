package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
 *  <bean id="viewResolver"
        class="org.springframework.web.servlet.view.InternalResourceViewResolver"
        p:prefix="/"
        p:suffix=".jsp"
     />
 */
@Configuration
public class BoardViewResolverConfig {
   @Bean("viewResolver")
   public ViewResolver viewResolver()
   {
	   InternalResourceViewResolver r=new InternalResourceViewResolver();
	   r.setPrefix("/");
	   r.setSuffix(".jsp");
	   return r;
   }
}
