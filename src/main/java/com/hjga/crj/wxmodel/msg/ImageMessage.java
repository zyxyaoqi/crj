package com.hjga.crj.wxmodel.msg;

public class ImageMessage extends BaseMessage {
	private String PicUrl;
	private long MediaId;
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public long getMediaId() {
		return MediaId;
	}
	public void setMediaId(long mediaId) {
		MediaId = mediaId;
	}
	 
	
}
