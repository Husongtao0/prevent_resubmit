package com.itheima.prs.web.action;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.itheima.prs.utils.TokenProccessor;
import com.itheima.prs.utils.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ClassName:TokenAction <br/>
 * Function: <br/>
 * Date: 2018年3月23日 下午5:04:06 <br/>
 */
@Namespace("/")
@ParentPackage("struts-default")
public class TokenAction extends ActionSupport implements ModelDriven<User> {
    private static final long serialVersionUID = 1L;

    private User model = new User();

    @Override
    public User getModel() {
        return model;
    }

    @Action(value = "tokenAction_save",
            interceptorRefs = {@InterceptorRef("token"), @InterceptorRef("defaultStack")},
            results = {@Result(name = "invalid.token", location = "/invalid.jsp"),
                    @Result(name = "success", location = "/success.jsp"),
                    @Result(name = "error", location = "/form_action.jsp")})
    public String save() throws Exception {

        // 判断用户名输入框是否为空
        // 不能使用model.getUsername()==""
        // 不能使用model.getUsername()==null判断为空
        // 可以使用model.getUsername().trim().length()==0
        // 可以使用model.getUsername().trim().isEmpty()//return value.length == 0;
        // struts2可以使用StringUtils.isEmpty(model.getUsername().trim())
        if (StringUtils.isEmpty(model.getUsername().trim())) {
            ServletActionContext.getRequest().setAttribute("error", "请输入用户名");
            return ERROR;
        }
        
        try {
            // 让当前的线程睡眠2秒钟，模拟网络延迟
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("向数据库中保存数据：" + model.getUsername().trim());
        return SUCCESS;
    }
    
    //AJAX生成token
    @Action("tokenAction_token")
    public String token() throws IOException {
        ServletActionContext.getResponse().getWriter().write(TokenProccessor.getInstance().getToken());
        return NONE;
    }
}
