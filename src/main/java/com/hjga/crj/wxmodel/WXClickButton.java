package com.hjga.crj.wxmodel;

public class WXClickButton extends WXButton{
	
	private String key;

	public WXClickButton(String name, String type, String key) {
		super(name, type);
		this.key = key;
	}
	public WXClickButton(String name, String type, String key,WXButton[] sub_button) {
		super(name, type,sub_button);
		this.key = key;
	}
	

}
