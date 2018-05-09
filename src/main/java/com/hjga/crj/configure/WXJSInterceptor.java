package com.hjga.crj.configure;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hjga.crj.prop.WXConfigProp;
import com.hjga.crj.task.WXAccessTokenTask;
import com.hjga.crj.util.HashKit;
import com.hjga.crj.wxmodel.token.JsapiTicket;

public class WXJSInterceptor implements HandlerInterceptor{
	
	private static Logger log = LoggerFactory.getLogger(WXJSInterceptor.class);
	
	@Autowired
	private WXConfigProp wxContants;
	
	/**
	 * 请求包含jssdk的页面时，拦截请求
	 * 获取jssdk使用ticket
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("incoming a jssdk request");
		JsapiTicket ticket = WXAccessTokenTask.getJsTicket();
		String nonce_str = create_nonce_str();
		// 注意 URL 一定要动态获取，不能 hardcode.
		String url = "http://" + request.getServerName() // 服务器地址
				// + ":"
				// + getRequest().getServerPort() //端口号
				+ request.getContextPath() // 项目名称
				+ request.getServletPath();// 请求页面或其他地址
		String qs = request.getQueryString(); // 参数
		if (qs != null) {
			url = url + "?" + (request.getQueryString());
		}
		String timestamp = create_timestamp();
		// 这里参数的顺序要按照 key 值 ASCII 码升序排序
		//注意这里参数名必须全部小写，且必须有序
		String  str = "jsapi_ticket=" + ticket.getTicket() +
        "&noncestr=" + nonce_str +
        "&timestamp=" + timestamp +
        "&url=" + url;

		String signature = HashKit.sha1(str);
		
		log.debug("  nonceStr " + nonce_str + " timestamp " + timestamp);
		log.debug("url " + url + " signature " + signature);
		log.debug("nonceStr " + nonce_str + " timestamp " + timestamp);
		log.debug(" jsapi_ticket " + ticket.getTicket());
		log.debug("nonce_str  " + nonce_str);
		
		request.setAttribute("nonceStr", nonce_str);
		request.setAttribute("timestamp", timestamp);
		request.setAttribute("url", url);
		request.setAttribute("signature", signature);
		request.setAttribute("jsapi_ticket", ticket.getTicket());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}
}
