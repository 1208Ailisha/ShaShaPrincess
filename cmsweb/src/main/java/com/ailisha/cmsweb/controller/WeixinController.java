package com.ailisha.cmsweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zengjx
 * @version $Id: WeixinController.java 2017/12/28 zengjx $
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {
    @RequestMapping("/index")
    public String index(Model model){
        return "weixin/index";
    }
}
