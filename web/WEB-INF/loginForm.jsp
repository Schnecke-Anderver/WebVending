

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <p align="center"><img src="mebelny-Almeda.jpg" width="800px;" alt="Здесь должна быть иллюстрация главной страницы"/></p>        
        <h1 align="center">Введите логин и пароль</h1> 
    <p align="center">${info}</p>
    <form action="login" method="POST" align="center">
        Логин <input type="text" name="login" value=""><br><br>
        Пароль <input type="password" name="password" value=""><br><br>
        <input type="submit" value="Войти"><br>
    </form>
    <p align="center">Еще не с нами? <a href="registrationForm">Зарегистрироваться</a></p>
    <p align="center"><a href="index.jsp" >На главную</a></p>
 
