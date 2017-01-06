<%-- 
    Document   : admin-users
    Created on : 03.11.2015, 14:26:43
    Author     : crowfoot
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>admin users</title>
    </head>
    <body>
        <h1>admin users</h1>
        <table>
            
        <c:forEach  items="${users}" var="user" >
            <tr>
                
                <td></td>
                <td> ${user.login}         </td>
                <td> ${user.name}         </td>
                <form method="POST" action="./serv">
                <td> <input type="edit" name="access" value="${user.access}"/>  </td>
                <td>
                        
                        <input type="hidden" name="command" value="adm_update_users">
                        <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" name="submit" value="update">
                        
                   </td> 
                </form>
            <form method="POST" action="./serv">
                <td>
                    <input type="hidden" name="command" value="adm_delete_user">
                        <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" name="submit" value="delete">
                </td>
            </form>
            </tr>
        </c:forEach>
            
            
        </table></br>
        
    </body>
</html>
