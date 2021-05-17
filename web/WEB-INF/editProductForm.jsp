<%-- 
    Document   : editProductForm
    Created on : 10.05.2021, 9:40:50
    Author     : Juri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Редактирование записи о продукте</h1>
        <p>${info}</p>
        
        <form action="editProduct" method="POST">
            <input type="hidden" name="productId" value="${product.id}">
            Наименование товара: <input type="text" name="name" value="${product.name}"><br><br>
            Цена: <input type="text" name="price" value="${product.price}"><br><br>
            Количество: <input type="text" name="quantity" value="${product.quantity}"><br><br>
            <input type="submit" name="submit" value="Изменить">
        </form><br>
        <p align="center"><a href="index.jsp">На главную</a></p>
    
