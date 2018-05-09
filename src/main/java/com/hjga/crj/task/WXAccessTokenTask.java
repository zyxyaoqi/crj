package com.hjga.crj.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.hjga.crj.constant.WXApiURL;
import com.hjga.crj.prop.WXConfigProp;
import com.hjga.crj.wxmodel.token.AccessToken;
import com.hjga.crj.wxmodel.token.JsapiTicket;

@Component
public class WXAccessTokenTask {

	private static Logger log = LoggerFactory.getLogger(WXAccessTokenTask.class);
	
	@Autowired
	private WXConfigProp wxContants;


	private static AccessToken accessToken = new AccessToken();
	private static JsapiTicket jsTicket = new JsapiTicket();
	
	public static JsapiTicket getJsTicket() {
		return jsTicket;
	}


	public static String getAccessToken(){
		return accessToken.getAccess_token();
	}
	
	
	/**
	 * 定时从微信服务器上面获取token值
	 */
	@Scheduled(fixedRate = 7000000) //// 测试每7000秒执行一次
	public void requestForAccessToken() {
		log.info("start task to  getAccessToken");
		RestTemplate restTemplate = new RestTemplate();
		String tokenUrl = WXApiURL.ACCESS_TOKEN_REQ_URL.replace("APPID", wxContants.getAppid()).replace("APPSECRET",
				wxContants.getAppSecret());
		accessToken = restTemplate.getForObject(tokenUrl, AccessToken.class);
		log.info("access_token:" + accessToken.getAccess_token());
		log.info(">>expires_in:" + accessToken.getExpires_in());
		log.info(">>expires_error_msg:" + accessToken.getErrmsg());
		
		jsTicket = restTemplate.getForObject(WXApiURL.JSAPI_TICKET_REQ_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token()), JsapiTicket.class);
		log.info("jssdk_ticket:" + accessToken.getAccess_token());
		log.info(">>expires_in:" + accessToken.getExpires_in());
		log.info(">>expires_error_msg:" + accessToken.getErrmsg());
	}
	
}
