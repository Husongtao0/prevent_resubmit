<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>form_servlet</title>
</head>
<script type="text/javascript">
	//方式一
	/* function dosubmit() {
		//将表单提交按钮设置为不可用，这样就可以避免用户再次点击提交按钮
		document.getElementById("submit").disabled = "disabled";
		//返回true让表单可以正常提交
		return true;
	} */
	//方式二
	/* var isCommitted = false;//表单是否已经提交标识，默认为false
	function dosubmit() {
		if (isCommitted == false) {
			isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true
			return true;//返回true让表单正常提交
		} else {
			return false;//返回false那么表单将不提交
		}
	} */
</script>
<body>

	<form action="${pageContext.request.contextPath}/doTokenServlet"
		method="post" onsubmit="return dosubmit()">
		<%--使用隐藏域存储生成的token--%>
		<input type="hidden" name="token" value="${token}" />
		用户名
		<input type="text" name="username">
		<font color="red">${error }</font>
		<br />
		<input type="submit" value="注册" id="submit">
	</form>

</body>
</html>