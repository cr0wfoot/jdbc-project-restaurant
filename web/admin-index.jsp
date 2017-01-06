<%-- 
    Document   : admin-index
    Created on : 03.11.2015, 14:19:42
    Author     : crowfoot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin</title>
    </head>
    <body>
        <h1>admin</h1>
        <table>
        <c:forEach  items="${orders}" var="order" >
            <tr>
                <td> ${order.id}         </td>
                <td> ${order.userId}         </td>
                <td>
                <form method="POST" action="./serv">
                    <input type="hidden" name="command" value="adm_get_order">
                    <input type="hidden" name="id" value="${order.id}">
                    <input type="submit" name="submit" value="see">
                </form> 
                </td>
            </tr>
        </c:forEach>
        </table></br>
        <form method="POST" action="./serv">
                    <input type="hidden" name="command" value="adm_get_users">
                    <input type="submit" name="submit" value="users">
                </form>
    </body>
</html>
