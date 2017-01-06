<%-- 
    Document   : user-order
    Created on : 03.11.2015, 14:04:36
    Author     : crowfoot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>order</title>
    </head>
    <body>
        <h1>order</h1>
        ${order.id}</br>
        ${order.time}</br>
        <table>
        <c:forEach  items="${ingredients}" var="ingredient" >
            <tr>
                <td> ${ingredient.name}         </td>
                <td> ${ingredient.price}         </td>
                
            </tr>
        </c:forEach>
            </table>
    </body>
</html>
