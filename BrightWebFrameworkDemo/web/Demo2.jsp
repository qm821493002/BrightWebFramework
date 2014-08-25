<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2014/8/7
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form  method="get" action="index" >
    <input  type="text" name="name"  value="${book.name}"/>
    <br>
    <input type="text" name="age" value="${book.age}">
    <br>
    <input  type="submit"   />

</form>
</body>
</html>
