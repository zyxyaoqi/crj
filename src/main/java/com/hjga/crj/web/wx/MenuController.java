package com.hjga.crj.web.wx;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hjga.crj.serverice.WXMenuService;
import com.hjga.crj.task.WXAccessTokenTask;
import com.hjga.crj.wxmodel.menu.Button;
import com.hjga.crj.wxmodel.menu.ClickButton;
import com.hjga.crj.wxmodel.menu.Menu;
import com.hjga.crj.wxmodel.menu.ViewButton;

@RestController
@RequestMapping("/wx/menu")
public class MenuController {
	private static Logger log = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private WXMenuService menuService;

	// 查询全部菜单
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getMenu() {
		// 调用接口获取access_token
		String at = WXAccessTokenTask.getAccessToken();
		if (at != null) {
			// 调用接口查询菜单
			return menuService.getMenu(at);
		}
		log.info("token为" + at);
		return "无数据";
	}

	// 创建菜单
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public int createMenu() {
		// 调用接口获取access_token
		String at = WXAccessTokenTask.getAccessToken();
		int result = 0;
		if (at != null) {
			ClickButton b1 = new ClickButton("我的", "click", "11");
			ClickButton b2 = new ClickButton("首页", "click", "22");
			ViewButton b3 = new ViewButton("连接", "view", "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxafb6d8f5995f4bc7&redirect_uri=http%3A%2F%2Fgi5bpj.natappfree.cc%2Fhome&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
			Menu m = new Menu();
			List<Button> l = new ArrayList<Button>();
			l.add(b1);
			l.add(b2);
			l.add(b3);
			m.setButton(l.toArray(new Button[3]));
			// 调用接口创建菜单
			result = menuService.createMenu(m, at);
			// 判断菜单创建结果
			if (0 == result) {
				log.info("菜单创建成功！");
			} else {
				log.info("菜单创建失败，错误码：" + result);
			}
		}
		return result;
	}

	// 删除菜单
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int deleteMenu() {
		// 调用接口获取access_token
		String at = WXAccessTokenTask.getAccessToken();
		int result = 0;
		if (at != null) {
			// 删除菜单
			result = menuService.deleteMenu(at);
			// 判断菜单删除结果
			if (0 == result) {
				log.info("菜单删除成功！");
			} else {
				log.info("菜单删除失败，错误码：" + result);
			}
		}
		return result;
	}

}
