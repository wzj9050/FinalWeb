<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>
<%@ page import = "java.util.*" %>
<%
    //取出当前session的username并在olUserList中删除
    String username = (String)session.getAttribute("username");
    List olUserList = (List)application.getAttribute("olUserList");
    //如果取出来的是空的，要初始化
    if(olUserList == null)
    {
        olUserList = new ArrayList();
    }
    //把username添加进去
    olUserList.remove(username);
    application.setAttribute("olUserList", olUserList);
//销毁会话
    session.invalidate();
%>

<form action="login.jsp">
    <input type = "submit" value = "Login" />
</form>
<h1>Login out successfully!</h1>
</body>
</html>
