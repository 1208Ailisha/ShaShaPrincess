package com.ailisha.cmsweb.weixin.msg;

/**
 * 消息封装工具类的基类,这里采用泛型,方便后期功能扩展
 * @author zengjx
 * @version $Id: BaseMessageUtil.java 2017/12/27 zengjx $
 */
public interface BaseMessageUtil<T> {
    /**
     * 将回复的信息对象转xml格式给微信
     * @param message
     * @return
     */
    public abstract String messageToxml(T t);

    /**
     * 回复的信息封装
     * @param FromUserName
     * @param ToUserName
     * @param Content
     * @return
     */
    public abstract String initMessage(String FromUserName, String ToUserName,String content);
}
