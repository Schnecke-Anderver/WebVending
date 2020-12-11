<%-- 
    Document   : listProducts
    Created on : 11.12.2020, 10:36:49
    Author     : Dilerom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список продукции</title>
    </head>
    <body>
        <h1>Мы предлагаем:</h1>
        <ul>
            <c:forEach var="product" items="${listProducts}" varStatus="status">
                <li>
                    ${status.index}. ${product.name}. ${product.price}. ${product.quantity}
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
