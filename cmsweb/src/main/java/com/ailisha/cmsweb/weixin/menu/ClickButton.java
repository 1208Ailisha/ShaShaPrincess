package com.ailisha.cmsweb.weixin.menu;

/**
 * 点击类型
 *
 * @author zengjx
 * @version $Id: ClickButton.java 2017/12/27 zengjx $
 */
public class ClickButton extends Button{
    private String key;//菜单KEY值

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
