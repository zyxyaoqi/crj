package com.hjga.crj.constant;

public class WXApiURL {
	/**
	 * weixin web页面跳转授权 access_token 请求 url
	 */
	public static String WEB_ACCESSTOKEN_REQ_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	/**
	 * weixin 开发模式下的access_token请求 url
	 */
	public  static  String  ACCESS_TOKEN_REQ_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**
	 * weixin jssdk ticket请求URL
	 */
	public static String JSAPI_TICKET_REQ_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	
	/**
	 * 使用授权获取用户信息
	 */
	public static String GET_USER_INFO_URL ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 使用微信网页授权获取用户信息
	 */
	public static String GET_WEB_USER_INTO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 创建二维码
	 */
	public static String CREATE_SCENE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	
	/**
	 * 根据ticket获取二维码图片地址
	 */
	public static String SCENE_PIC_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
}
