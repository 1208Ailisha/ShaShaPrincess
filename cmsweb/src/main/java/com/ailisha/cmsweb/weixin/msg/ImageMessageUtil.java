package com.ailisha.cmsweb.weixin.msg;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;

import com.ailisha.cmsweb.weixin.msg.image.Image;
import com.ailisha.cmsweb.weixin.msg.image.ImageMessage;
import com.ailisha.cmsweb.weixin.util.UploadUtil;
import com.ailisha.cmsweb.weixin.util.WeiXinUtil;
import com.thoughtworks.xstream.XStream;

/**
 * @author zengjx
 * @version $Id: ImageMessageUtil.java 2017/12/27 zengjx $
 */
public class ImageMessageUtil implements BaseMessageUtil<ImageMessage> {

    /**
     * <xml>
     *     <ToUserName><![CDATA[toUser]]></ToUserName>
     *     <FromUserName><![CDATA[fromUser]]></FromUserName>
     *     <CreateTime>12345678</CreateTime>
     *     <MsgType><![CDATA[image]]></MsgType>
     *     <Image>
     *         <MediaId><![CDATA[media_id]]></MediaId>
     *     </Image>
     * </xml>
     */

    /**
     * 将信息转为xml格式
     */
    public String messageToxml(ImageMessage imageMessage) {
        XStream xtream = new XStream();
        xtream.alias("xml", imageMessage.getClass());
        xtream.alias("Image", new Image().getClass());
        return xtream.toString();
    }


    /**
     * 封装信息
     */
    public String initMessage(String FromUserName, String ToUserName, String content) {
        Image image = new Image();
        image.setMediaId(getmediaId());
        ImageMessage message = new ImageMessage();
        message.setFromUserName(ToUserName);
        message.setToUserName(FromUserName);
        message.setCreateTime(new Date().getTime());
        message.setImage(image);
        return messageToxml(message);
    }

    /**
     * 获取Media
     *
     * @return
     */
    public String getmediaId() {
        String path = "f:/1.jpg";
        String accessToken = WeiXinUtil.getAccess_Token();
        String mediaId = null;
        try {
            mediaId = UploadUtil.upload(path, accessToken, "image");
        } catch (KeyManagementException | NoSuchAlgorithmException
                | NoSuchProviderException | IOException e) {
            e.printStackTrace();
        }
        return mediaId;
    }
}
