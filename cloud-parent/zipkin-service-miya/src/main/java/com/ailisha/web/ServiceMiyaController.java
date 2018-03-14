package com.ailisha.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zengjx
 * @version $Id: ServiceMiyaController.java 2018/1/12 zengjx $
 */
@RestController
public class ServiceMiyaController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hi")
    public String home(){
        return "hi i'm miya!";
    }

    @RequestMapping("/miya")
    public String info(){
        return restTemplate.getForObject("http://localhost:8988/toHiService",String.class);
    }


}
