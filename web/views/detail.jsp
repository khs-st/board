<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.kb.www.vo.ArticleVO" %>
<%
    ArticleVO vo2=(ArticleVO)request.getAttribute("num");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 detail</title>
    <style>
        h1, table {
            text-align: center;
            margin: auto;
        }

        table, tr, td {
            width: 1000px;
            height: 100px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<div>
    <table>
        <tr>
            <td>글번호</td>
            <td>제목</td>
        </tr>
        <tr>
            <td><%=vo2.getArticleNum()%>
            </td>
            <td>
                <%=vo2.getArticleTitle()%>
            </td>
        </tr>
    </table>

</div>
</body>
</html>
