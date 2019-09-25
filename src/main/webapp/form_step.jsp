<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ru.lanit.javaweb.FormStepServlet" %>

<html>
<head>
    <title>Form Step Demo</title>
</head>
<body>

<h2>Form step #<%= FormStepServlet.getStepNumber()%></h2>
<div>
    <form action="" method="get">
        <label>
            <input type="text" name="name" placeholder="<%= FormStepServlet.getPlaceHolder()%>"/>
        </label>
        <br/>
        <button type="submit" formaction="form_step">Next</button>
    </form>
</div>
</body>
</html>