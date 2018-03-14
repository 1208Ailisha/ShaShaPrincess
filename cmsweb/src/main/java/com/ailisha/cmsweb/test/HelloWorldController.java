package com.ailisha.cmsweb.test;

import com.ailisha.cmsweb.domain.UserEntity;
import com.ailisha.cmsweb.domain.UserSexEnum;
import com.ailisha.cmsweb.mail.MailService;
import com.ailisha.cmsweb.mappers.UserMapper;
import com.ailisha.cmsweb.redis.RedisService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hspcadmin on 2017/12/4.
 */
@RestController
public class HelloWorldController {

    Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    RedisService redisService;

    @RequestMapping("/hello")
    public String index(HttpSession session) {
        System.out.println(">>>>>>>>>>>>>>>>>index"+session.getAttribute("uid"));
        if(session.getAttribute("uid")==null || StringUtils.isEmpty(session.getAttribute("uid").toString())){
            return "no login";
        } else {
            return session.getAttribute("uid").toString();
        }

    }

    @RequestMapping("/mail/simple")
    public String simpleMail() {
        mailService.sendSimpleMail("872087731@qq.com","subject xxxxx","this is a test mail");
        return "";
    }

    @RequestMapping("/mail/template")
    public String templateMail() {
        mailService.sendTemplateMail("872087731@qq.com","subject xxxxx","this is a test mail");
        return "";
    }

    @RequestMapping("/user/list")
    @Cacheable(value="user-key")//其中value的值就是缓存到redis中的key
    public List<UserEntity> userList() {
        List<UserEntity> users = (List<UserEntity>)redisService.getObj("users");
        if(CollectionUtils.isEmpty(users)){
            System.out.println("调用数据库查询了");
            users = userMapper.getAll();
            redisService.setObj("users", users);
        } else {
            System.out.println("从redis取数据");
        }

        System.out.println(users);

        return users;
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        System.out.println(">>>>>>>>>uid" + uid);
        //return session.getId();
        return uid.toString();
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Map<String, Object> firstResp (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        map.put("sessionId", request.getSession().getId());
        map.put("request Url", request.getRequestURL());
        request.getSession().setAttribute("map", map);
        return map;
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        return map;
    }

}
