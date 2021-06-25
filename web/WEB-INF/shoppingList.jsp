<%-- 
    Document   : shoppingList
    Created on : Jun 18, 2021, 3:20:37 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello ${username}! <a href="ShoppingList?action=logout">Logout</a></p>

        <form action="" method="POST">
            <h2>List</h2>
            <label>Add item: </label>
            <input type="text" name="iteminput"><input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>
        
        <form action="" method="post">
            
            <ul>
                <c:forEach items="${itemList}" var="item"> 
                    <li style="list-style: none;"><input type="radio" name="itemselect" value="${item}">${item}</li>
                </c:forEach>
            </ul>
            
            
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form> 
            
    </body>
</html>
