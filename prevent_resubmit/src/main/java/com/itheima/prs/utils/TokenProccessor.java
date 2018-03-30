package com.itheima.prs.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Token;
import org.apache.struts2.util.TokenHelper;

import com.opensymphony.xwork2.ActionContext;

import sun.misc.BASE64Encoder;

/**
 * ClassName:TokenProccessor <br/>
 * Function: <br/>
 * Date: 2018年3月23日 下午2:24:49 <br/>
 */
@SuppressWarnings("restriction")
public class TokenProccessor {
    /*
     * 单例设计模式（保证类的对象在内存中只有一个） 1、把类的构造函数私有 2、自己创建一个类的对象 3、对外提供一个公共的方法，返回类的对象
     */
    private TokenProccessor() {}

    private static final TokenProccessor instance = new TokenProccessor();

    /**
     * 返回类的对象
     * @return
     */
    public static TokenProccessor getInstance() {
        return instance;
    }

    /**
     * 生成Token Token：Nv6RRuGEVvmGjB+jimI/gw==
     * @return
     */
    public String makeToken() { // checkException
        // 7346734837483 834u938493493849384 43434384
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        // 数据指纹 128位长 16个字节 md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.getBytes());//生成摘要
            // base64编码--任意二进制编码明文字符 adfsdfsdfsf
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String getToken() {
        /*String salt="("+request.getParameter("username")+")";    
        String md5Pwd=new Md5Hash(request.getParameter("password"),salt).toString();*/
        //String token=new Md5Hash(UUID.randomUUID().toString()).toString().toUpperCase();
        
        //String token = (new BigInteger(165, new SecureRandom())).toString(36).toUpperCase();
        //String token = TokenHelper.generateGUID();
        
        //TokenHelper.setToken("struts.token.name");
        
        //struts生成token的原理
        HashMap<String,Object> map = new HashMap<String,Object>();
        
        map.put("struts.token.name", new String[] {"token"});
        
        ActionContext.getContext().setParameters(map);
        
        String token = TokenHelper.setToken(TokenHelper.getTokenName());
        
        System.out.println(token);
        
        return token;
    }
}
