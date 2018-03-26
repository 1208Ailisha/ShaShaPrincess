package com.ailisha.cmsweb.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zengjx
 * @version $Id: SpringLifeCycleServiceProcessor.java 2018/3/22 zengjx $
 */
@Component
public class SpringLifeCycleAware implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("SpringLifeCycleAware的：" + beanFactory.getBean("springBeanLifeCycle"));
    }
}
