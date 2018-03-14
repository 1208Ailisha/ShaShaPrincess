package com.ailisha.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 重试机制：
 * http://blog.didispace.com/spring-cloud-ribbon-failed-retry/
 * @author zengjx
 * @version $Id: HiService.java 2018/1/11 zengjx $
 */
@Service
public class HiService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "sorry " + name + ",服务暂不可用!";
    }
}
