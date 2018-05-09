package com.hjga.crj.web.wx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hjga.crj.constant.WXMessageType;
import com.hjga.crj.util.WXMessageUtil;
import com.hjga.crj.web.WXRetrunMessageCreator;
import com.hjga.crj.wxmodel.msg.Article;

@RestController
@RequestMapping("/wx")
public class MsgController {
	
	private static Logger log = LoggerFactory.getLogger(MsgController.class);	
	
	/**
	 * 获取微信公众号服务器推送过来的消息，根据业务逻辑对消息进行处理
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String post(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String> requestMap;
		String respMessage = null;
		try {
			requestMap = WXMessageUtil.parseXml(req);
			String respContent = "请求处理异常，请稍候尝试！";
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");
			
			log.debug("收到微信消息："+ msgType);

			switch(msgType){
				case WXMessageType.REQ_MESSAGE_TYPE_TEXT:{
					String content = requestMap.get("Content");
					if (isQqFace(content)) {
						respContent = WXMessageUtil.createTextMessage(fromUserName, toUserName, content) ;
					} else{
						switch(content){
						case "1":{
							content = WXRetrunMessageCreator.createTextContent();
							respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, content) ;
							break;
						}
						case "11":{
							List<Article> articleList = new ArrayList<Article>();
							WXRetrunMessageCreator.createNewsContent(articleList, 1);
							respMessage = WXMessageUtil.createNewsMessage(fromUserName, toUserName, articleList) ;
							break;
						}
						case "12":{
							List<Article> articleList = new ArrayList<Article>();
							WXRetrunMessageCreator.createNewsContent(articleList, 3);
							respMessage = WXMessageUtil.createNewsMessage(fromUserName, toUserName, articleList) ;
							break;
						}
						case "13":{
							content = "<a href=\"http://www.baidu.com\">百度主页</a>";
							respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, content) ;
							break;
						}
						default:{
							content = "很抱歉，现在小8暂时无法提供此功能给您使用。\n\n回复“1”显示帮助信息";
							respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, content) ;
							break;
						}
						}
					}
					break;
				}
				case WXMessageType.REQ_MESSAGE_TYPE_IMAGE:{
					respContent = "你发的是图片";
					respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, respContent) ;
					break;
				}
				case WXMessageType.REQ_MESSAGE_TYPE_LOCATION:{
					respContent = "你发的是地理位置";
					respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, respContent) ;
					break;
				}
				case WXMessageType.REQ_MESSAGE_TYPE_VOICE:{
					respContent = "你发的是声音";
					respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, respContent) ;
					break;
				}
				case WXMessageType.REQ_MESSAGE_TYPE_EVENT:{
	                // 事件类型
	                String eventType =requestMap.get("Event");
	                // 点击菜单id
	    			String EventKey = requestMap.get("EventKey");
	                // 自定义菜单点击事件
	                 if (eventType.equals(WXMessageType.EVENT_TYPE_CLICK)) {
	                    switch (EventKey){
	                        case "11":{
	                            respContent = "第一个菜单";
	                            break;
	                        }
	                        case "22":{
	                            respContent = "第二个菜单";
	                            break;
	                        }
	                        default:{
	                            respContent= "很抱歉，此按键功能正在升级无法使用";
	                        }
	                    }
	                    respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, respContent) ;
	                }
	                 else if (eventType.equals(WXMessageType.EVENT_TYPE_VIEW)) {
	                	 String url = EventKey;
	                	 resp.sendRedirect(url);
	                 }
	                 else if(eventType.equals(WXMessageType.EVENT_TYPE_LOCATION)){
	                	 String latitude = requestMap.get("Latitude");
	                	 String longitude = requestMap.get("Longitude");
	                	respContent = "获取到你的位置信息,latitude=" + latitude + ", longitude=" +longitude;
	                	respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, respContent) ;
	                 }
	                 else if(eventType.equals(WXMessageType.EVENT_TYPE_SUBSCRIBE)){
	                	 respContent = "感谢你的关注，你传递的参数为" + EventKey;
	                	 respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, respContent) ;
	                 }
	                 else if(eventType.equals(WXMessageType.EVENT_TYPE_SCAN)){
	                	 respContent = "你已关注，你传递的参数为" + EventKey;
	                	 respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, respContent) ;
	                 }
	                 break;
				}
				default:{
					respContent = "事件类型为:" + msgType;
					respMessage = WXMessageUtil.createTextMessage(fromUserName, toUserName, respContent) ;
				}
			}
			 		 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

	/**
	 * 判断是否是QQ表情
	 *
	 * @param content
	 * @return
	 */
	private  boolean isQqFace(String content) {
		boolean result = false;
		// 判断QQ表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}
}
