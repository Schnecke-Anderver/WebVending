<%-- 
    Document   : listClients
    Created on : 11.12.2020, 11:13:06
    Author     : Dilerom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список клиентов</title>
    </head>
    <body>
        <h1>Наши клиенты:</h1>
        <ul>
            <c:forEach var="client" items="${listClients}" varStatus="status">
                <li>
                    ${status.index}. ${client.firstname}. ${client.lastname}. ${client.phone}. ${client.cash}
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
