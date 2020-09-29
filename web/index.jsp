<%@ page import="com.kb.www.common.LoginManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LoginManager lm = LoginManager.getInstance();
    String id = lm.getMemberId(session);
%>
<html>
<meta charset="UTF-8">
<head>
    <title>게시판</title>
</head>
<body>
<a href="/list.do">게시판이동!</a>
<br/>
<%
    if (id == null) {
%>
<a href="/join.do">회원가입</a><br/>
<br/>
<a href="/login.do">로그인</a>
<%
} else {
%>
<a href="history.do">히스토리</a>
<br/>
<a href="/logout.do">로그아웃</a>
<br/>
<a href="/leave.do">회원탈퇴</a>
<%
    }
%>
</body>
</html>
