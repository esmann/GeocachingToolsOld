<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 4/2/11
  Time: 6:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
    <head>
        <title>Simple jsp page</title>
        <link href="/css/default.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=da"></script>     <!-- TODO add language dependent on browser language -->
        <script type="text/javascript">
        function initialize()
        {
            var myLatlng = new google.maps.LatLng(55.65, 12.56);
            var myOptions =
            {
                zoom: 8,
                center: myLatlng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            }
            window.map = new google.maps.Map(document.getElementById("mapCanvas"), myOptions);
            loadCaches(); //TODO add bounding box
        }
</script>
    </head>
    <body onload="initialize()">
    <div id="mapCanvas"/>
        <%
            UserService userService = UserServiceFactory.getUserService();
            User user = userService.getCurrentUser();
            if (user != null) {
        %>


        <%
            } else {
        %>
        <p>Hello!
        <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
        <%
            }
        %>
    <script type="text/javascript" src="js/map.js"></script>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.js"></script>
    </body>
</html>