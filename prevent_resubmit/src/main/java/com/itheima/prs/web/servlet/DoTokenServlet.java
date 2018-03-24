package com.itheima.prs.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoTokenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        
        if (username.trim().length()==0) {
            request.setAttribute("error", "请输入用户名");
            request.getRequestDispatcher("/form_servlet.jsp").forward(request, response);
            return;
        }
        
        try {
            // 让当前的线程睡眠2秒钟，模拟网络延迟
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        boolean b = isRepeatSubmit(request);// 判断用户是否是重复提交
        if (b == true) {
            System.out.println("请求无效或请不要重复提交!");
            request.getRequestDispatcher("invalid.jsp").forward(request, response);
            return;
        }
        
        request.getSession().removeAttribute("token");// 移除session中的token
        
        request.setAttribute("username", username );
        System.out.println("向数据库中保存数据：" + request.getParameter("username").trim());
        request.getRequestDispatcher("/success.jsp").forward(request, response);
    }

    /**
     * 判断客户端提交上来的令牌和服务器端生成的令牌是否一致
     * @param request
     * @return true 用户重复提交了表单 false 用户没有重复提交表单
     */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String client_token = request.getParameter("token");
        // 1、如果用户提交的表单数据中没有token，（页面刷新）则用户是重复提交了表单
        if (client_token == null) {
            return true;
        }
        // 取出存储在Session中的token
        String server_token = (String) request.getSession().getAttribute("token");
        // 2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
        if (server_token == null) {
            return true;
        }
        // 3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if (!client_token.equals(server_token)) {
            return true;
        }
        return false;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
