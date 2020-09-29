<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="com.kb.www.vo.MemberHisoryVO" %>
<%@ page import="com.kb.www.common.Parser" %>
<%
    ArrayList<MemberHisoryVO> list = (ArrayList<MemberHisoryVO>) request.getAttribute("list");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>히스토리</title>
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
</head>

<body>
<div id="table">
<table>
    <tr>
        <td>구분</td>
        <td>발생일시</td>
    </tr>
    <% for(int i=0; i<list.size(); i++){%>
    <tr>
        <td>
            <%=Parser.parseMemberEventType(list.get(i).getEvt_type())%>
        </td>
        <td><%=list.get(i).getDttm()%></td>
    </tr>
    <%}%>
</table>
<button onclick="location.href='index.jsp'">홈으로 이동!</button>
</div>
</body>
</html>
