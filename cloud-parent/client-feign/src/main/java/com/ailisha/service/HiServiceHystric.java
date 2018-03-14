package com.ailisha.service;

import org.springframework.stereotype.Component;

/**
 * @author zengjx
 * @version $Id: HiServiceHystric.java 2018/1/11 zengjx $
 */
@Component
public class HiServiceHystric implements HiService {
    @Override
    public String sayHi(String name) {
        return "sorry " + name + ",服务暂不可用!";
    }
}
