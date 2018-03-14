package com.ailisha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.DiscoveryClient;

/**
 * @author zengjx
 * @version $Id: HiCloudController.java 2018/1/11 zengjx $
 */
@RestController
public class HiCloudController {
    @Value("${server.port}")
    String port;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        ServiceInstance instance = client.getLocalServiceInstance();
        System.out.println(instance.getHost());
        System.out.println(instance.getServiceId());
        return "hi " + name + ",i am from port:" + port;
    }
}
