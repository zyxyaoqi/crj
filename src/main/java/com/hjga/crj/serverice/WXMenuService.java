package com.hjga.crj.serverice;

import com.hjga.crj.wxmodel.menu.Menu;


public interface WXMenuService {
    public String getMenu(String accessToken);
    public int createMenu(Menu menu, String accessToken);
    public int deleteMenu(String accessToken);
}