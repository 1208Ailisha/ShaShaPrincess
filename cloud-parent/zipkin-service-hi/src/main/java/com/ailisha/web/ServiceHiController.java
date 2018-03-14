package com.ailisha.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zengjx
 * @version $Id: ServiceHiController.java 2018/1/12 zengjx $
 */
@RestController
public class ServiceHiController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hi")
    public String callHome(){
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }
    @RequestMapping("/toHiService")
    public String info(){
        return "i'm service-hi";

    }
}
