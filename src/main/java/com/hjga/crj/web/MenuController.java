package com.hjga.crj.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hjga.crj.serverice.WXMenuService;
import com.hjga.crj.util.WXAccessTokenUtil;
import com.hjga.crj.wxmodel.WXButton;
import com.hjga.crj.wxmodel.WXClickButton;
import com.hjga.crj.wxmodel.WXMenu;
import com.hjga.crj.wxmodel.WXViewButton;

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
		String at = WXAccessTokenUtil.accessToken.getAccess_token();
		if (at != null) {
			// 调用接口查询菜单
			return menuService.getMenu(at);
		}
		log.info("token为" + at);
		return "无数据";
	}

	// 创建菜单
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public int createMenu() {
		// 调用接口获取access_token
		String at = WXAccessTokenUtil.accessToken.getAccess_token();
		int result = 0;
		if (at != null) {
			WXClickButton b1 = new WXClickButton("我的", "click", "11");
			WXClickButton b2 = new WXClickButton("首页", "click", "22");
			WXViewButton b3 = new WXViewButton("首页", "view", "http://snzs86.natappfree.cc/home");
			WXMenu m = new WXMenu();
			List<WXButton> l = new ArrayList<WXButton>();
			l.add(b1);
			l.add(b1);
			//l.add(b1);
			m.setButton(l.toArray(new WXButton[0]));
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
		String at = WXAccessTokenUtil.accessToken.getAccess_token();
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
