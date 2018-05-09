package com.hjga.crj.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hjga.crj.prop.WXConfigProp;
 
@Controller
public class APPController  {
	 
 
	@Autowired
	private WXConfigProp appProp;
	
	@RequestMapping(value="/app", method = RequestMethod.GET)
	public String index(HttpServletRequest req, Model model) {
		model.addAttribute("appid", appProp.getAppid());
		model.addAttribute("nonceStr", req.getAttribute("nonceStr"));
		model.addAttribute("timestamp", req.getAttribute("timestamp"));
		model.addAttribute("url", req.getAttribute("url"));
		model.addAttribute("signature", req.getAttribute("signature"));
		model.addAttribute("jsapi_ticket", req.getAttribute("jsapi_ticket"));
		return "app/index";
	}
}
