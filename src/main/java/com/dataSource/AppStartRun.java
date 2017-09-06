package com.dataSource;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement // 启用事务
public class AppStartRun extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(AppStartRun.class);
	/**
	 * 设置上下文属性
	 * @return
	 */
	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return new ServletContextInitializer() {
			public void onStartup(ServletContext servletContext) throws ServletException {
				ServletContextHolder.setServletContext(servletContext);
			}
		};
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		builder.bannerMode(Banner.Mode.OFF);
		return builder.sources(AppStartRun.class); // 以 war 包形式发布时需要此设置
	}

	public static void main(String[] args) throws IOException {
		logger.info("aboot is start");
		SpringApplication.run(AppStartRun.class, args);
		logger.info("aboot is end.");
	}
}
