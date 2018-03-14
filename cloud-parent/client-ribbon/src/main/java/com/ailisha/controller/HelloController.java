package com.ailisha.controller;

import com.ailisha.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zengjx
 * @version $Id: HelloController.java 2018/1/11 zengjx $
 */
@RestController
public class HelloController {

    @Autowired
    private HiService hiService;

    @RequestMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return hiService.hiService(name);
    }

}
