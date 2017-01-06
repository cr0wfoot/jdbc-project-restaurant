<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>user-index</title>
    </head>
    <body>
        <h1>user-index</h1>
        ${user.name}</br>
        ${user.login}</br>
        <table>
        <c:forEach  items="${bills}" var="bill" >
            <tr>
                <td> ${bill.price}         </td>
                <td>
                <form method="POST" action="./serv">
                    <input type="hidden" name="command" value="usr_get_bill">
                    <input type="hidden" name="id" value="${bill.id}">
                    <input type="submit" name="submit" value="see">
                </form> 
                </td>
            </tr>
        </c:forEach>
        </table></br>
        <table>
        <c:forEach  items="${orders}" var="order" >
            <tr>
                <td> ${order.time}         </td>
                <td> ${order.id}         </td>
                <td>
                <form method="POST" action="./serv">
                    <input type="hidden" name="command" value="usr_get_order">
                    <input type="hidden" name="id" value="${order.id}">
                    <input type="submit" name="submit" value="see">
                </form> 
                </td>
            </tr>
        </c:forEach>
        </table></br>
        <form method="POST" action="./serv">
                    <input type="hidden" name="command" value="usr_make_order">
                    <input type="submit" name="submit" value="make order">
                </form> 
                    
    </body>
</html>
