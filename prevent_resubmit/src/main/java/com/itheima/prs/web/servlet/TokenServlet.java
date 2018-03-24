package com.itheima.prs.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.prs.utils.TokenProccessor;

public class TokenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = TokenProccessor.getInstance().makeToken();// 创建令牌
        System.out.println("在TokenServlet中生成的token：" + token);
        request.getSession().setAttribute("token", token); // 在服务器使用session保存token(令牌)
        request.getRequestDispatcher("/form_servlet.jsp").forward(request, response);// 跳转到form_servlet.jsp页面
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
