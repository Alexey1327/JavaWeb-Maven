<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="i18n"/>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="<c:out value="${sessionScope.language}"/>">
<body>
<h2><fmt:message key="title.index-page" /></h2>
<div><img src="images/image.png" alt="OldLady"></div>
<div style="margin-bottom: 20px">
    <label><fmt:message key="label.language" /></label>
    <a href="?language=en">English</a>
    <a href="?language=ru">Russian</a>
</div>
<div>
    <form action="" method="get">
        <label>
            <input type="text" name="name" placeholder="<fmt:message key="label.form-first-name" />"/>
        </label>
        <br/>
        <button type="submit" formaction="form_step"><fmt:message key="label.next" /></button>
    </form>
</div>
</body>
</html>
