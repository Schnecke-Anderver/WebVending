<%-- 
    Document   : listProducts
    Created on : 11.12.2020, 10:36:49
    Author     : Dilerom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <p align="center"><img src="mebelny-Almeda.jpg" width="800px;" alt="Здесь должна быть иллюстрация главной страницы"/></p> 
        <h1 align="center">Мы предлагаем:</h1>
         
        <ul type="none" align="center">
            <c:forEach var="product" items="${listProducts}" varStatus="status">
                <li>
                    ${status.index}.  ${product.name}.   Цена -  ${product.price} евро. Количество - ${product.quantity} шт. <a href="editProductForm?productId=${product.id}">Изменить</a>
                </li><br>
            </c:forEach>
        </ul>
        <p align="center"><a href="index.jsp" >На главную</a></p>
    
