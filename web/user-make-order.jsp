<%-- 
    Document   : user-make-order
    Created on : 03.11.2015, 14:00:02
    Author     : crowfoot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>make order</title>
    </head>
    <body>
        <h1>make order</h1>
        <table>
            <form method="POST" action="./serv">
            <c:forEach  items="${ingredients}" var="ingredient" >
            <tr>
                
                    <td><input type="checkbox" name="ingredients" value="${ingredient.id}" /></td>
                    <td> ${ingredient.name}         </td>
                    <td> ${ingredient.consist}         </td>
                    <td> ${ingredient.calorie}         </td>
                    <td> ${ingredient.weight}         </td>
                    <td> ${ingredient.price}         </td>
            </tr>
        </c:forEach>
            <input type="hidden" name="command" value="usr_insert_order">
                    <input type="submit" name="submit" value="insert">
            </form>
        </table>
    </body>
</html>
