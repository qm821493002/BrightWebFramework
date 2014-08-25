<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2014/7/30
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="bri" uri="/bright-tags" %>
<%@ taglib prefix="s" uri="/bright-tags" %>

<html>
<head>
    <title>titleds</title>
</head>
<body>
<%
    Date date = new Date();
    request.setAttribute("date",date);
%>
<bri:date format="yyyy/MM/dd hh:mm"  expression="request.date" />

${user}


</body>
</html>
