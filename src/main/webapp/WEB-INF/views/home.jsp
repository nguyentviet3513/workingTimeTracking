<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: nguyentviet3513
  Date: 3/25/2019
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app1.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css"
          href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>
</head>
<body>
<div id="mainWrapper">
    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
        <a href="<c:url value='//admin/list-user' />" class="btn btn-success home-button-width">User Management</a></br>
    </sec:authorize>
    <a href="<c:url value='//user/attendance-list-${loggedinuser}' />" class="btn btn-success home-button-width">Attendance</a>
</div>
</body>
</html>
