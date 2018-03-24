package com.itheima.prs.utils.domain;

import java.io.Serializable;

/**
 * ClassName:User <br/>
 * Function: <br/>
 * Date: 2018年3月24日 上午11:44:00 <br/>
 */
public class User implements Serializable {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 1L;
    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [username=");
        builder.append(username);
        builder.append("]");
        return builder.toString();
    }
}
