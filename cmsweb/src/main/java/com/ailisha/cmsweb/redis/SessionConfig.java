package com.ailisha.cmsweb.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * https://docs.spring.io/spring-session/docs/current/reference/html5/guides/boot.html
 * https://www.cnblogs.com/andyfengzp/p/6434287.html
 * http://blog.csdn.net/a60782885/article/details/70242086
 * 单点登录：https://www.cnblogs.com/hujunzheng/p/6395966.html?from=singlemessage
 * @author zengjx
 * @version $Id: SessionConfig.java 2017/12/28 zengjx $
 */
//maxInactiveIntervalInSeconds为SpringSession的过期时间（单位：秒）
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class SessionConfig {
}
