<%-- 
    Document   : addClientForm
    Created on : 11.12.2020, 10:57:12
    Author     : Dilerom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить клиента</title>
    </head>
    <body>
        <h1>Пополнить список клиентов</h1>
        <p>${info}</p> 
        <a href="index.jsp">На главную</a><br><br>
        <form action="createProduct" method="POST">
        Имя клиента: <input type="text" name="firstname" value="${firstname}"><br><br>
        Фамилия клиента: <input type="text" name="lastname" value="${lastname}"><br><br>
        Телефон: <input type="text" name="phone" value="${phone}"><br><br>
        Наличность: <input type="text" name="cash" value="${cash}"><br><br>
        <input type="submit" name="submit" value="Добавить">
    </form>
    </body>
</html>
