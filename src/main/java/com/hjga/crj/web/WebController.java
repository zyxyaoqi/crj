package com.hjga.crj.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String checkName(@RequestParam(name = "openid", defaultValue = "") String openid) {
		return openid;
	}

}
