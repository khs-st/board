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

        table, tr, td {
            width: 1000px;
            height: 100px;
            border: 1px solid black;
        }
    </style>
    <script src="https://code.jquery.com/jquery-1.11.3.js"
            type="text/javascript"></script>

    <script type="text/javascript">
        function ShowDetail(articleNum){
            location.href="detail?num="+articleNum;
        }
        $(document).ready(function ()
        {
            $('tr').hover(function(){
                $(this).css('color','blue');
            }, function() {
                $(this).css('color','black');
            });
        });
    </script>
</head>
<body>
<h1>GitHub 커밋테스트!</h1>
<div>
    <table>
        <tr>
            <td>글번호</td>
            <td>글제목</td>
            <td>글내용</td>
            <td>조회수</td>
            <td>작성일자</td>
        </tr>
        <%
            if (list.size() > 0) {
                for(int i = 0; i < list.size(); i++) {
        %>
        <tr onclick="ShowDetail(<%=list.get(i).getArticleNum()%>)" >
            <td><%=list.get(i).getArticleNum()%>
            </td>
            <td>
                <%=list.get(i).getArticleTitle()%>
            </td>
            <td>
                <%=list.get(i).getArticleContent()%>
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
            <td colspan="4">게시글이 없습니다.</td>
        </tr>
        <%
            }
        %>
        <form action="add" method="post">
            글제목: <input type="text" name="subject"/><br/>
            글내용: <input type="text" name="content"/><br/>
            <input type="submit" value="글쓰기"/>
        </form>

    </table>

</div>
</body>
</html>
