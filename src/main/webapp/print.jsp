<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="ru.lanit.javaweb.servlets.PrintServlet" %>
<html>
<head>
    <title>Form filled!</title>
</head>
<body>
    <h2>Congratulations, <%= PrintServlet.getFullName(request)%>! You've successfully filled the form!</h2>
    <a href="index.jsp">Start again</a>
</body>
</html>
