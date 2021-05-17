<%-- 
    Document   : saleOfProductForm
    Created on : 16.12.2020, 13:00:09
    Author     : Dilerom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <p align="center"><img src="mebelny-Almeda.jpg" width="800px;" alt="Здесь должна быть иллюстрация главной страницы"/></p>        
        <h1 align="center">Оформить заказ</h1> 
        <p class="w-100 text-info align-items-center">${info}</p>    
        
        <form action="saleOfProduct" method="POST" align="center">
            <p>Выберите клиента:</p>
            <select name="clientId">
            <option value="">Выберите клиента</option> 
            <c:forEach var="client" items="${listClients}" varStatus="status">
                <option value="${client.id}">${client.firstname} ${client.lastname}. ${client.phone}. ${client.cash}.</option>
            </c:forEach>
            </select><br> 
            <p>Выберите мебель и количество:</p>
            <select name="productId">
            <option value="">Выберите мебель </option>
            <c:forEach var="product" items="${listProducts}" varStatus="status">
                <option value="${product.id}"> ${product.name}. Цена ${product.price} евро. ${product.quantity} шт.</option> <br>             
            </c:forEach>                
            <br>
            Количество: <input type="text" name="count" value="">  <br><br>
            <input type="submit" value="Купить">
            </form>
        <p align="center"> <a href="index.jsp">На главную</a></p>
    
