package com.ailisha.cmsweb.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Ref:
 * https://my.oschina.net/crossoverjie/blog/1648284
 * 实现 BeanPostProcessor 接口，Spring 中所有 bean 在做初始化时都会调用该接口中的两个方法，可以用于对一些特殊的 bean 进行处理
 * @author zengjx
 * @version $Id: SpringLifeCycleService.java 2018/3/22 zengjx $
 */
@Component
public class SpringLifeCycleService implements InitializingBean,DisposableBean,BeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(SpringLifeCycleService.class);

    @Override
    public void destroy() throws Exception {
        logger.error("SpringLifeCycleService的destroy被调用");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        logger.error("SpringLifeCycleService的afterPropertiesSet被调用");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("springBeanLifeCycle".equals(beanName)){
            logger.error("SpringLifeCycleService的postProcessBeforeInitialization被调用");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("springBeanLifeCycle".equals(beanName)){
            logger.error("SpringLifeCycleService的postProcessAfterInitialization被调用");
        }
        return bean;
    }
}
