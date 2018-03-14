package com.ailisha.cmsweb.weixin.msg;

import com.ailisha.cmsweb.weixin.msg.text.MessageText;
import com.thoughtworks.xstream.XStream;

import java.util.Date;

/**
 * @author zengjx
 * @version $Id: TextMessageUtil.java 2017/12/27 zengjx $
 */
public class TextMessageUtil implements BaseMessageUtil<MessageText>{
    /**
     * 将发送消息封装成对应的xml格式
     */
    public  String messageToxml(MessageText message) {
        XStream xstream  = new XStream();
        xstream.alias("xml", message.getClass());
        return xstream.toXML(message);
    }
    /**
     * 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
     * @param FromUserName
     * @param ToUserName
     */
    public  String initMessage(String FromUserName, String ToUserName,String content) {
        MessageText text = new MessageText();
        text.setToUserName(FromUserName);
        text.setFromUserName(ToUserName);
        text.setContent("欢迎关注******公众号");//您输入的内容是
        text.setCreateTime(new Date().getTime());
        text.setMsgType("text");
        return  messageToxml(text);
    }
}
