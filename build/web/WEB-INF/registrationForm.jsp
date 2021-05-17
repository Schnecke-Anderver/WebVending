<%-- 
    Document   : addClientForm
    Created on : 11.12.2020, 10:57:12
    Author     : Dilerom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <p align="center"><img src="mebelny-Almeda.jpg" width="800px;" alt="Здесь должна быть иллюстрация главной страницы"/></p>        
        <h1 align="center">Пополнить список клиентов</h1> 
        <p class="w-100 text-info align-items-center">${info}</p> 
        
        <div align="center">
            <form action="registration" method="POST">
                Имя клиента: <input type="text" name="firstname" value="${firstname}"><br><br>
                Фамилия клиента: <input type="text" name="lastname" value="${lastname}"><br><br>
                Телефон: <input type="text" name="phone" value="${phone}"><br><br>
                Наличность: <input type="text" name="cash" value="${cash}"><br><br>
                Логин: <input type="text" name="login" value="${login}"><br><br>
                Пароль: <input type="password" name="password" value="${password}"><br><br>
                <input type="submit" name="submit" value="Добавить">
            </form>
        <a href="index.jsp">На главную</a>
        </div>    
       
