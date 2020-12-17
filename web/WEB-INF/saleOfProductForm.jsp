<%-- 
    Document   : saleOfProductForm
    Created on : 16.12.2020, 13:00:09
    Author     : Dilerom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Сделка</title>
    </head>
    <body>
        <h1>Оформить заказ</h1>
        <p>${info}</p> 
        <a href="index.jsp">На главную</a><br><br>
        
        <form action="saleOfProduct" method="POST">
            <p>Выберите клиента:</p>
            <select name="clientId">
            <option value="">Выберите клиента</option>
            <c:forEach var="client" items="${listClients}" varStatus="status">
                <option value="${client.id}">${client.firstname} ${client.lastname}. ${client.phone}. ${client.cash}.</option>
            </c:forEach>
            </select>
            <p>Выберите мебель:</p>
            <select name="productId">
            <option value="">Выберите мебель</option>
            <c:forEach var="product" items="${listProducts}" varStatus="status">
                <option value="${product.id}"> ${product.name}. ${product.price}. ${product.quantity}</option>                
            </c:forEach>                
            <br><br>
            <p>Количество: </p><input type="text" name="count" value="">   
            <br><br>
            <input type="submit" value="Купить">
        </form>
    </body>
</html>
