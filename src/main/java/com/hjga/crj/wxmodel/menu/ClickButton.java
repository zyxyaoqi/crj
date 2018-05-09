package com.hjga.crj.wxmodel.menu;

public class ClickButton extends Button{
	
	private String key;

	public ClickButton(String name, String type, String key) {
		super(name, type);
		this.key = key;
	}
	public ClickButton(String name, String type, String key,Button[] sub_button) {
		super(name, type,sub_button);
		this.key = key;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	

	
}
