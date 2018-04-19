package com.hjga.crj.serverice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hjga.crj.util.WXAccessTokenUtil;
import com.hjga.crj.wxmodel.WXMenu;
import com.hjga.crj.wxmodel.WXMenuReturnStatus;

@Service("menuService")
public class WXMenuServiceImpl implements WXMenuService {

	private static Logger log = LoggerFactory.getLogger(WXMenuServiceImpl.class);

    // 菜单创建（POST） 限1000（次/天）
    public static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    // 菜单查询（POST） 限10000（次/天）
    public static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    // 菜单删除（POST） 限1000（次/天）
    public static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	@Override
	public String getMenu(String accessToken) {
		RestTemplate restTemplate =new RestTemplate();
		String url = MENU_GET_URL.replace("ACCESS_TOKEN", WXAccessTokenUtil.accessToken.getAccess_token());
		ResponseEntity<String>  respos = restTemplate.getForEntity(url, String.class);
		
		return respos.getBody();
	}

	@Override
	public int createMenu(WXMenu menu, String accessToken) {
		RestTemplate restTemplate =new RestTemplate();
		String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", WXAccessTokenUtil.accessToken.getAccess_token());
		ResponseEntity<WXMenuReturnStatus> responseEntity = restTemplate.postForEntity(url, menu, WXMenuReturnStatus.class);
	    return responseEntity.getBody().getErrcode();
	}

	@Override
	public int deleteMenu(String accessToken) {
		RestTemplate restTemplate =new RestTemplate();
		String url = MENU_DELETE_URL.replace("ACCESS_TOKEN", WXAccessTokenUtil.accessToken.getAccess_token());
		ResponseEntity<WXMenuReturnStatus>  respos = restTemplate.getForEntity(url, WXMenuReturnStatus.class);
		return respos.getBody().getErrcode();
	}

}
