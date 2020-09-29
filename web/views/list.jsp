<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.kb.www.vo.ArticleVO" %>
<%
    ArrayList<ArticleVO> list = (ArrayList<ArticleVO>) request.getAttribute("list");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>IntelliJ 게시판</title>
    <style>
        h1, table {
            text-align: center;
            margin: auto;
        }

        table,tr, td {
            height: 30px;
            border: 1px solid black;
        }

        td{
            width:100px;
        }
        #table {
            text-align: center;
            margin: auto;
            width: 600px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-1.11.3.js"
            type="text/javascript"></script>

    <script type="text/javascript">
        function ShowDetail(articleNum) {
            location.href = "detail.do?num=" + articleNum;
        }

        $(document).ready(function () {
            $('tr').hover(function () {
                $(this).css('color', 'blue');
            }, function () {
                $(this).css('color', 'black');
            });
        });
    </script>
</head>
<h1>GitHub 커밋테스트!</h1>
<br/>
<body>
<div id="table">
    <table>
        <tr>
            <td>글번호</td>
            <td>글제목</td>
            <td>작성자</td>
            <td>조회수</td>
            <td>작성일자</td>
        </tr>
        <%
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
        %>
        <tr onclick="ShowDetail(<%=list.get(i).getArticleNum()%>)">
            <td><%=list.get(i).getArticleNum()%>
            </td>
            <td>
                <%=list.get(i).getArticleTitle()%>
            </td>
            <td>
                <%=list.get(i).getId()%>
            </td>
            <td>
                <%=list.get(i).getHit()%>
            </td>
            <td>
                <%=list.get(i).getWriteDate()%>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">게시글이 없습니다.</td>
        </tr>
        <%
            }
        %>
    </table>
    <br/>
    <form action="/write.do">
        <input type="submit" value="글쓰기"/>
    </form>
    <button onclick="location.href='index.jsp'">홈으로 이동!</button>
</div>

</body>
</html>
