package com.dataSource.common;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataBaseConfig {

	@Bean(name = "readDataSource")
	@ConfigurationProperties("spring.ds_reader")
	public DruidDataSource readDataSource() {
		return new DruidDataSource();
	}

	@Bean
	@ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
	public SqlSessionFactory dynamicSqlSessionFactory(@Qualifier("readDataSource") DataSource readDataSource,
			MybatisProperties properties) {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(readDataSource);
		sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(properties.getConfigLocation()));
		sessionFactory.setMapperLocations(properties.resolveMapperLocations());
		try {
			return sessionFactory.getObject();
		} catch (Exception e) {
			throw new DateSourceException(e);
		}
	}

}
