package com.hjga.crj;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.hjga.crj.util.AccessToken;
import com.hjga.crj.util.WXAccessTokenUtil;
import com.hjga.crj.web.WXProperties;


@EnableScheduling
@SpringBootApplication
public class CrjApplication {

	private static Logger log = LoggerFactory.getLogger(CrjApplication.class);
	
	@Autowired
	private WXProperties wxContants;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CrjApplication.class, args);
	}
	
	/**
	 * 定时从微信服务器上面获取token值
	 */
	//@Scheduled(fixedRate=20000) ////测试每20秒执行一次
	 @Scheduled(cron="0/30 * *  * * ? ")   //测试每5秒执行一次
	//@Scheduled(cron="0/7000 * *  * * ? ")   //每7000秒执行一次
	//@Scheduled(cron = "0 0 */2 * * ?")  //俩小时执行一次
	public void getAccessToken() {
		log.info("into  getAccessToken");
		RestTemplate restTemplate =new RestTemplate();
		String  tokenUrl=WXAccessTokenUtil.ACCESS_TOKEN_URL.replace("APPID",wxContants.getAppid()).replace("APPSECRET",wxContants.getAppSecret());
		WXAccessTokenUtil.accessToken=restTemplate.getForObject(tokenUrl,AccessToken.class);
		log.info("access_token:"+WXAccessTokenUtil.accessToken.getAccess_token());
		log.info("expires_in:"+WXAccessTokenUtil.accessToken.getExpires_in());
		log.info("expires_in:"+WXAccessTokenUtil.accessToken.getErrmsg());
	}
}
