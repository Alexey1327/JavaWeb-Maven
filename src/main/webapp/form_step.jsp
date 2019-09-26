<%@ page contentType="text/html;charset=UTF-8" errorPage ="error.jsp"%>
<%@ page import="ru.lanit.javaweb.servlets.FormStepServlet" %>
<%@ page errorPage ="error.jsp"%>
<html>
<head>
    <title>Form Step Demo</title>
</head>
<body>

<h2>Form step #<%= FormStepServlet.getNextStepNumber(request)%></h2>
<div>
    <form action="" method="get">
        <label>
            <input type="text" name="name" placeholder="<%= FormStepServlet.getPlaceHolder(request)%>"/>
        </label>
        <br/>
        <button type="submit" formaction="form_step">Next</button>
    </form>
</div>
</body>
</html>