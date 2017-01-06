<%-- 
    Document   : register
    Created on : 03.11.2015, 13:44:17
    Author     : crowfoot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>reg</title>
    </head>
    <body>
        <h1>reg</h1>
        <form method="POST" action="./serv">
            <input type="edit" name="login" />
            <input type="password" name="password" />
            <input type="edit" name="name" />
            <input type="hidden" name="command" value="register" />
            <input type="submit" name="submit" value="enter"/>
        </form>
    </body>
</html>
