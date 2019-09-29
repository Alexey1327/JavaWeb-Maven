<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.lanit.javaweb.servlets.FormStepServlet" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n"/>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="${sessionScope.language}">
<body>
<h2><fmt:message key="label.step" /><%= FormStepServlet.getNextStepNumber(request)%></h2>
<div>
    <form action="" method="get">
        <label>
            <input type="text" name="name" placeholder="<%= FormStepServlet.getPlaceHolder(request)%>"/>
        </label>
        <br/>
        <button type="submit" formaction="form_step"><fmt:message key="label.next" /></button>
    </form>
</div>
</body>
</html>