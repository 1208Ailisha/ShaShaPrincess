package com.ailisha.cmsweb.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author zengjx
 * @version $Id: SpringBeanLifeCycle.java 2018/3/22 zengjx $
 */
@Component("springBeanLifeCycle")
public class SpringBeanLifeCycle {

    private Logger logger = LoggerFactory.getLogger(SpringBeanLifeCycle.class);

    //最先执行
    public SpringBeanLifeCycle() {
        logger.error("SpringBeanLifeCycle的constructor方法被调用!");
    }

    //构造方法执行之后执行,可以用来加载一些缓存数据
    @PostConstruct
    public void start(){
        logger.error("SpringBeanLifeCycle的PostConstruct方法被调用!");
    }

    //关闭或者启动异常执行
    @PreDestroy
    public void destory(){
        logger.error("SpringBeanLifeCycle的PreDestroy方法被调用!");
    }
}
