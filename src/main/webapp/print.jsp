<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n"/>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="ru.lanit.javaweb.servlets.PrintServlet" %>

<!DOCTYPE html>
<html lang="${sessionScope.language}">
<body>
<h2><%= PrintServlet.getSuccessMessage(request)%></h2>
    <a href="index.jsp"><fmt:message key="label.print-page-go-to-start" /></a>
</body>
</html>