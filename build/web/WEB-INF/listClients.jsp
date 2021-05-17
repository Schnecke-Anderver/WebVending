<%-- 
    Document   : listClients
    Created on : 11.12.2020, 11:13:06
    Author     : Dilerom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Наши клиенты:</h1>
         <a href="index.jsp">На главную</a><br><br>
        <ul>
            <c:forEach var="client" items="${listClients}" varStatus="status">
                <li>
                    ${status.index}. ${client.firstname}. ${client.lastname}. ${client.phone}. ${client.cash}
                </li>
            </c:forEach>
        </ul>
         <p align="center"><a href="index.jsp">На главную</a></p>
   