package com.hjga.crj.wxmodel.menu;

public class ViewButton extends Button {
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ViewButton(String name, String type, String url, Button[] sub_button) {
		super(name, type,sub_button);
		this.url = url;
	}
	
	public ViewButton(String name, String type, String url) {
		super(name, type);
		this.url = url;
	}
	

}
