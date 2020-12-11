<%-- 
    Document   : addProductForm
    Created on : 11.12.2020, 7:03:08
    Author     : Dilerom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить мебель</title>
    </head>
    <body>
        <h1>Добавить мебель</h1>
        <p>${info}</p> 
        
        <a href="index.jsp">На главную</a><br><br>
         <form action="createProduct" method="POST">
        Наименование товара: <input type="text" name="name" value="${name}"><br><br>
        Цена: <input type="text" name="price" value="${price}"><br><br>
        Количество: <input type="text" name="quantity" value="${quantity}"><br><br>
        <input type="submit" name="submit" value="Добавить">
    </form>
    </body>
</html>
 