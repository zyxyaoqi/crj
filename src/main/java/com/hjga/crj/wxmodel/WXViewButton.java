package com.hjga.crj.wxmodel;

public class WXViewButton extends WXButton {
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public WXViewButton(String name, String type, String url, WXButton[] sub_button) {
		super(name, type,sub_button);
		this.url = url;
	}
	
	public WXViewButton(String name, String type, String url) {
		super(name, type);
		this.url = url;
	}
	

}
