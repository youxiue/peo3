<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  UserInfo: Administrator
  Date: 2018/10/27/027
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>用户信息列表</title>
</head>
<body>
    <table border="1px" align="center">
        <tr>
            <td>用户编号</td>
            <td>用户名</td>
            <td>工作职位</td>
            <td>月薪</td>
            <td>奖金</td>
        </tr>
        <c:forEach items="${empList}" var="emp">
            <tr>
                <td>${emp.empno}</td>
                <td>${emp.ename}</td>
                <td>${emp.job}</td>
                <td>${emp.sal}</td>
                <td>${emp.comm}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
