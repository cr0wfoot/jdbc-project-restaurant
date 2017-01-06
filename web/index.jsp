<%-- 
    Document   : index
    Created on : 31.10.2015, 9:35:16
    Author     : crowfoot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
    </head>
    <body>
        <h1>index</h1>
        <form method="POST" action="./serv">
            <input type="edit" name="login" />
            <input type="password" name="password" />
            <input type="hidden" name="command" value="login" />
            <input type="submit" name="submit" value="enter"/>
        </form>
        <a href="register.jsp">registration</a>
    </body>
</html>
