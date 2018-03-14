package com.ailisha.cmsweb.weixin.msg.image;

import com.ailisha.cmsweb.weixin.msg.BaseMessage;

/**
 * 图片消息
 *
 * @author zengjx
 * @version $Id: ImageMessage.java 2017/12/27 zengjx $
 */
public class ImageMessage extends BaseMessage {

    private Image Image;//Image节点

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}
