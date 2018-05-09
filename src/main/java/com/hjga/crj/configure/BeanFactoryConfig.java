package com.hjga.crj.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.hjga.crj.util.WxMappingJackson2HttpMessageConverter;

@Configuration
public class BeanFactoryConfig {
	
	/**
	 * 统一提供http请求bean
	 * @return
	 */
	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
		return restTemplate;
	}

	
}
