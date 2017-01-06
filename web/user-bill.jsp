<%-- 
    Document   : user-bill
    Created on : 03.11.2015, 14:11:13
    Author     : crowfoot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>bill</title>
    </head>
    <body>
        <h1>bill</h1>
        ${bill.id}</br>
        ${bill.price}</br>
        <form method="POST" action="./serv">
                    <input type="hidden" name="command" value="usr_pay_bill">
                    <input type="hidden" name="id" value="${bill.id}">
                    <input type="submit" name="submit" value="pay">
                </form>
    </body>
</html>
