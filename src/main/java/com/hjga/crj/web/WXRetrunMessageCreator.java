package com.hjga.crj.web;

import java.util.Date;
import java.util.List;

import com.hjga.crj.constant.WXMessageType;
import com.hjga.crj.util.WXMessageUtil;
import com.hjga.crj.wxmodel.msg.Article;
import com.hjga.crj.wxmodel.msg.NewsMessage;
import com.hjga.crj.wxmodel.msg.TextMessage;

public class WXRetrunMessageCreator {
	
	public static String createTextContent(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好，我是小8，请回复数字选择服务：").append("\n\n");
		buffer.append("11 可查看测试单图文").append("\n");
		buffer.append("12  可测试多图文发送").append("\n");
		buffer.append("13  可测试网址").append("\n");
		buffer.append("或者您可以尝试发送表情").append("\n\n");
		return buffer.toString();
	}
	
	public static void createNewsContent(List<Article> articleList, int len){
		// 多图文发送
		Article article1 = new Article();
		article1.setTitle("紧急通知，不要捡这种钱！湛江都已经传疯了！\n");
		article1.setDescription("");
		article1.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
		article1.setUrl(
				"http://mp.weixin.qq.com/s?__biz=MjM5Njc2OTI4NQ==&mid=2650924309&idx=1&sn=8bb6ae54d6396c1faa9182a96f30b225&chksm=bd117e7f8a66f769dc886d38ca2d4e4e675c55e6a5e01e768b383f5859e09384e485da7bed98&scene=4#wechat_redirect");

		articleList.add(article1);
		if(len==1)
			return ;
		Article article2 = new Article();
		article2.setTitle("湛江谁有这种女儿，请给我来一打！");
		article2.setDescription("");
		article2.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
		article2.setUrl(
				"http://mp.weixin.qq.com/s?__biz=MjM5Njc2OTI4NQ==&mid=2650924309&idx=2&sn=d7ffc840c7e6d91b0a1c886b16797ee9&chksm=bd117e7f8a66f7698d094c2771a1114853b97dab9c172897c3f9f982eacb6619fba5e6675ea3&scene=4#wechat_redirect");

		Article article3 = new Article();
		article3.setTitle("以上图片我就随意放了");
		article3.setDescription("");
		article3.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
		article3.setUrl(
				"http://mp.weixin.qq.com/s?__biz=MjM5Njc2OTI4NQ==&mid=2650924309&idx=3&sn=63e13fe558ff0d564c0da313b7bdfce0&chksm=bd117e7f8a66f7693a26853dc65c3e9ef9495235ef6ed6c7796f1b63abf1df599aaf9b33aafa&scene=4#wechat_redirect");

		articleList.add(article2);
		articleList.add(article3);
	}

}
