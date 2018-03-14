package com.ailisha.cmsweb.weixin.action;

import com.ailisha.cmsweb.weixin.msg.ImageMessageUtil;
import com.ailisha.cmsweb.weixin.util.CheckUtil;
import com.ailisha.cmsweb.weixin.msg.MessageUtil;
import com.ailisha.cmsweb.weixin.msg.TextMessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 与微信对接登陆验证
 * http://blog.csdn.net/shenbug/article/details/78748346
 *
 * @author zengjx
 * @version $Id: LoginAction.java 2017/12/27 zengjx $
 */
@Controller
public class LoginAction {
    @RequestMapping(value = "wx", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("success");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                out.write(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    /**
     * 处理post请求，将信息返回到微信平台
     * @param request
     * @param response
     */
    @RequestMapping(value = "wxdemo", method = RequestMethod.POST)
    public void dopost(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String, String> map = MessageUtil.xmlToMap(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");

        String message = "success";
        //处理文本类型，实现输入1，回复相应的封装的内容
        if ("text".equals(MsgType)) {
            if("图片".equals(Content)){
                ImageMessageUtil util = new ImageMessageUtil();
                message = util.initMessage(FromUserName, ToUserName,"");
            }else{
                TextMessageUtil textMessage = new TextMessageUtil();
                message = textMessage.initMessage(FromUserName, ToUserName,Content);
            }
        }
        try {
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }
}
