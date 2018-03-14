package com.ailisha.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * fallback 断路器，需要实现当前接口
 *
 * @author zengjx
 * @version $Id: HiService.java 2018/1/11 zengjx $
 */
@FeignClient(value = "service-hi", fallback = HiServiceHystric.class)
public interface HiService {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHi(@RequestParam(value = "name") String name);
}
