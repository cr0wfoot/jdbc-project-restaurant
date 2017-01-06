<%-- 
    Document   : admin-order
    Created on : 03.11.2015, 14:32:48
    Author     : crowfoot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>adm ord</title>
    </head>
    <body>
        <h1>adm ord</h1>
        ${order.id}</br>
        ${order.userId}</br>
        <form method="POST" action="./serv">
                    <input type="hidden" name="command" value="adm_accept_order">
                    <input type="hidden" name="id" value="${order.id}">
                    <input type="submit" name="submit" value="accept">
                </form>
    </body>
</html>
