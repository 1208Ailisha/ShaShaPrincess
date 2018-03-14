package com.ailisha.cmsweb.weixin.util;

import com.ailisha.cmsweb.redis.RedisService;
import com.ailisha.cmsweb.weixin.token.AccessToken;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zengjx
 * @version $Id: TokenUtil.java 2018/1/10 zengjx $
 */
@Service
public class TokenUtil {

    @Autowired
    RedisService redisService;

    /**
     * 开发者id
     */
    private static final String APPID = "换成你自己的appid";
    /**
     * 开发者秘钥
     */
    private static final String APPSECRET = "开发秘钥";
    /**
     * 获取ACCESS_TOKEN的URL
     */
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 处理doget请求
     *
     * @param url
     * @return
     */
    public static JSONObject doGetstr(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }

    /**
     * 处理post请求
     *
     * @param url
     * @return
     */
    public static JSONObject doPoststr(String url, String outStr) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "utf-8"));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

    public AccessToken getAccessToken() {
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        JSONObject json = doGetstr(url);
        if (json != null) {
            token.setAccess_token(json.getString("access_token"));
            token.setExpires_in(json.getInt("expires_in"));
            redisService.setStr("access_token", json.getString("access_token"),90, TimeUnit.MINUTES);
        }
        return token;
    }

    /**
     * 获取凭证
     *
     * @return
     */
    public String getAccess_Token() {
        System.out.println("从缓存中读取");
        String access_token = redisService.getStr("access_token");
        if (access_token == null) {
            AccessToken token = getAccessToken();
            access_token = token.getAccess_token();
        }
        return access_token;
    }

    public static void main(String[] args) {
        String access_token = WeiXinUtil.getAccess_Token();
        System.out.println("调用成功access_token:"+access_token);
    }
}
