<%-- 
    Document   : RequestManagerHeader
    Created on : Jun 28, 2022, 3:04:32 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Request Manager</title>
    </head>
    <body>
        <c:url var="logoutLink" value="MainController">
            <c:param name="action" value="Logout"/>
        </c:url>
        <c:url var="RequestManagement" value="MainController">
            <c:param name="action" value="LoadAllRequestManager"/>
        </c:url>
        <div class="navbar">
            <a class="active"href="${logoutLink}"><i class="fa fa-sign-out"></i> Logout</a>
            <a href="${RequestManagement}"><i class="fa fa-users"></i> User Management</a>
        </div>    
    </body>
</html>
