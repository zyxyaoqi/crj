package com.hjga.crj.serverice;

import com.hjga.crj.wxmodel.WXMenu;


public interface WXMenuService {
    public String getMenu(String accessToken);
    public int createMenu(WXMenu menu, String accessToken);
    public int deleteMenu(String accessToken);
}