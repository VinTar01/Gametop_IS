<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pagina JSP con Data e Ora</title>
</head>
<body>
    <h1>Benvenuto nella Pagina JSP</h1>

    <%-- Ottenere la data e l'ora correnti --%>
    <% Date currentDate = new Date(); %>

    <%-- Formattare la data e l'ora --%>
    <% java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); %>
    <% String formattedDate = dateFormat.format(currentDate); %>

    <p></p>Data e ora correnti: <%= formattedDate %></p>
</body>
</html>
