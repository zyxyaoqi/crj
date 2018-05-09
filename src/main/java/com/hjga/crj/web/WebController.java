package com.hjga.crj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hjga.crj.constant.WXApiURL;
import com.hjga.crj.prop.WXConfigProp;
import com.hjga.crj.serverice.WXMenuService;
import com.hjga.crj.task.WXAccessTokenTask;
import com.hjga.crj.wxmodel.UserInfo;
import com.hjga.crj.wxmodel.token.WebAccessToken;


@RestController
public class WebController {
	@Autowired
	private WXConfigProp appconfig;
	
	@Autowired
	private RestTemplate restTemplate;
 
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String checkName(@RequestParam(name = "code", defaultValue = "") String code) {
		
		String url = WXApiURL.WEB_ACCESSTOKEN_REQ_URL.replace("APPID", appconfig.getAppid()).replace("SECRET", appconfig.getAppSecret()).replace("CODE", code);
		WebAccessToken token= restTemplate.getForObject(url,WebAccessToken.class);
		if(token.getErrcode() == 0)
		{
			url = WXApiURL.GET_USER_INFO_URL.replace("ACCESS_TOKEN", WXAccessTokenTask.getAccessToken()).replace("OPENID", token.getOpenid());
			UserInfo user = restTemplate.getForObject(url, UserInfo.class);
			System.out.println(user.getNickname());
		}
		return code;
	}

	 
}
