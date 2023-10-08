package com.jk.board.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:/application.properties")
@Configuration
public class DatabaseConfig {

	private final ApplicationContext context;

	public DatabaseConfig(ApplicationContext context) {
		this.context = context;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(DataSource dataSource) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory(dataSource));
	}
}
