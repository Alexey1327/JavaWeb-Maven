<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.lanit.javaweb.PrintServlet" %>
<html>
<head>
    <title>Form filled!</title>
</head>
<body>
    <h2>Congratulations, <%= PrintServlet.getFullName()%>! You've successfully filled the form!</h2>
    <a href="index.jsp">Start again</a>
</body>
</html>
