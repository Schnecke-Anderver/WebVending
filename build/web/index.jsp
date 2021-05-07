<%-- 
    Document   : index
    Created on : 11.12.2020, 7:28:43
    Author     : Juri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebVending</title>
    </head>
    <body>
        <p><img src="mebelny-Almeda.jpg" width="900px;" alt="Здесь должна быть иллюстрация главной страницы"/></p>
        <h1>Добро пожаловать в магазин мягкой мебели Альмеда!</h1> <br>
        <p align="center">${info}</p>      <br> 
        
        <a href="logout">Выход</a><br>
        <a href="loginForm">Вход</a><br>
        
        <a href="addProduct">Добавить продукт</a> <br><br>
        <a href="listProducts">Перечень всей продукции</a> <br><br>
        <a href="listClients">Список клиентов</a> <br><br>
       <!-- <a href="addClient">Добавить клиента</a> <br><br> -->
        <a href="registrationForm">Добавить клиента</a><br>
        <a href="saleOfProductForm">Сделка</a> <br><br>
    </body>
</html>
