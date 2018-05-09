package com.hjga.crj.wxmodel;

public class UserInfo {
	private int subscribe;
	private String openid;
	private String nickname;
	private int sex;
	private String headimgurl;
	private long subscribe_time;
	private String subscribe_scene;
	private int groupid;
	private int[] tagid_list;
	private int qr_scene;
	private String qr_scene_str;
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	 
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int[] getTagid_list() {
		return tagid_list;
	}
	public void setTagid_list(int[] tagid_list) {
		this.tagid_list = tagid_list;
	}
	public int getQr_scene() {
		return qr_scene;
	}
	public void setQr_scene(int qr_scene) {
		this.qr_scene = qr_scene;
	}
	public String getQr_scene_str() {
		return qr_scene_str;
	}
	public void setQr_scene_str(String qr_scene_str) {
		this.qr_scene_str = qr_scene_str;
	}
	public long getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public void setSubscribe_scene(String subscribe_scene) {
		this.subscribe_scene = subscribe_scene;
	}
	public String getSubscribe_scene() {
		return subscribe_scene;
	}
	
	

}
