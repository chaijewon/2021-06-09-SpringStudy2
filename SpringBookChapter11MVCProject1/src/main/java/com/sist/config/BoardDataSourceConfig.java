package com.sist.config;



import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 *   <bean id="ds"
       class="org.apache.commons.dbcp.BasicDataSource"
       p:driverClassName="#{db['driver']}"
       p:url="#{db['url']}"
       p:username="#{db['username']}"
       p:password="#{db['password']}"
       p:maxActive="#{db['maxActive']}"
       p:maxIdle="#{db['maxIdle']}"
       p:maxWait="#{db['maxWait']}"
    />
    <!-- 트랜잭션 적용 : TransactionManager (미정) -->
    <!-- 데이터베이스 정보 : MyBatis로 전송 (SqlSessionFactoryBean) : getConnection/disConnection -->
    <bean id="ssf"
        class="org.mybatis.spring.SqlSessionFactoryBean"
        p:dataSource-ref="ds"
    />
    <!-- SqlSessionFactoryBean을 이용해서 인터페이스 구현 : MapperFactoryBean -->
    <bean id="mapper"
        class="org.mybatis.spring.mapper.MapperFactoryBean"
        p:sqlSessionFactory-ref="ssf"
        p:mapperInterface="com.sist.dao.BoardMapper"
    />
    SpEL
 */
@Configuration
// 라이브러리를 사용 할 경우에 사용 
public class BoardDataSourceConfig {
   @Bean("ds")
   public DataSource dataSource()
   {
	   BasicDataSource ds=new BasicDataSource();
	   ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	   ds.setUrl("jdbc:oracle:thin:@211.238.142.181:1521:XE");
	   ds.setUsername("hr");
	   ds.setPassword("happy");
	   ds.setMaxActive(20);
	   ds.setMaxIdle(15);
	   ds.setMaxWait(-1);
	   return ds;
   }
   @Bean("ssf")
   public SqlSessionFactory sqlSessionFactory() throws Exception
   {
	   SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
	   ssf.setDataSource(dataSource());
	   return ssf.getObject();
   }
   @Bean("mapper")
   public MapperFactoryBean mapperFactoryBean() throws Exception
   {
	   MapperFactoryBean mf=new MapperFactoryBean();
	   mf.setSqlSessionFactory(sqlSessionFactory());
	   mf.setMapperInterface(com.sist.dao.BoardMapper.class);
	   return mf;
   }
}






