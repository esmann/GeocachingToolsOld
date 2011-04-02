<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 3/27/11
  Time: 3:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Simple jsp page</title>
    </head>
    <body>
        <%
            UserService userService = UserServiceFactory.getUserService();
            User user = userService.getCurrentUser();
            if (user != null)
            {
        %>
        <form method='POST' enctype='multipart/form-data' action='/uploadpq'>
            File to upload: <input type=file name=upfile><br>
            <input type=submit value=Press> to upload the file!
        </form>
        <%
        } else
        {
        %>
        <p>Hello!
        <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
        <%
        }
        %>
    </body>
</html>