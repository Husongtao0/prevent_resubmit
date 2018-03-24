<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>form_action.jsp</title>
</head>
<body>

	<%-- <s:form action="tokenAction_save.action" theme="simple">  
	用户名:<s:textfield name="username" />
		  <!--服务器通过token标签 来产生盾牌随机数-->
		  <s:token /><br/>
		  <s:submit value="注册" />
	</s:form> --%>


	<form action="${pageContext.request.contextPath}/tokenAction_save.action" method="post">
		<!--服务器通过token标签 来产生盾牌随机数-->
		<s:token />
		用户名
		<input type="text" name="username">
		<font color="red">${error }</font><br/>
		<input type="submit" value="注册">
	</form>
	
</body>
</html>