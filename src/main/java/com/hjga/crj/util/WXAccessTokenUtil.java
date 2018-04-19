package com.hjga.crj.util;

public class WXAccessTokenUtil {

	public  static  String  ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


	public static AccessToken accessToken = new AccessToken();
}
