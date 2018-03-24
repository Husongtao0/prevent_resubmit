package com.itheima.prs.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoTokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
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
	    
	    request.setAttribute("username", username );
	    System.out.println("向数据库中保存数据："+ username.trim());
	    request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
