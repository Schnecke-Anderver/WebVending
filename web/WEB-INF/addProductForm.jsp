<%-- 
    Document   : addProductForm
    Created on : 11.12.2020, 7:03:08
    Author     : Dilerom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <p align="center"><img src="mebelny-Almeda.jpg" width="800px;" alt="Здесь должна быть иллюстрация главной страницы"/></p> 
        <h1>Добавить мебель</h1>         
        <p>${info}</p>         
        
        <form action="createProduct" method="POST">
        Наименование товара: <input type="text" name="name" value="${name}"><br><br>
        Цена: <input type="text" name="price" value="${price}"><br><br>
        Количество: <input type="text" name="quantity" value="${quantity}"><br><br>
        <input type="submit" name="submit" value="Добавить">
        </form><br>
        <p align="center"><a href="index.jsp">На главную</a></p>
    
 