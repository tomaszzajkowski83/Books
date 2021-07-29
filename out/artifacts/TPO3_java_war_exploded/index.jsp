<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Biblioteka PJATKowego człowieka</title>
    <link rel="stylesheet" href="./style.css"/>
</head>
<body>
<%
    Locale locale = request.getLocale();
    ResourceBundle msg = ResourceBundle.getBundle(
            "international.Messages", locale);
    session.setAttribute("resourceBundle",msg);
    String username = msg.getString("username");
    String password = msg.getString("password");
    String login = msg.getString("login");
    String sign = msg.getString("sign");
    String hello = msg.getString("hello");
    String inituser = "";
    if(session.getAttribute("login")!=null){
        String identifier = (String) session.getAttribute("login");
        hello = msg.getString(identifier);
        inituser = request.getParameter("login");
    }
%>
<h1><b>Biblioteka PJATKowego Człowieka</b></h1>
<hr>
<div style="text-align: center; background-color: aqua"><h2><%=hello%></h2></div>
<hr>
<div style="text-align: center; background-image: url('pjatk.png')">
    <form method="post" action="http://localhost:8080/TPO3/credentials"><br><br>
        <span style="color: gold"><%=username%></span>&nbsp;&nbsp;<input type="text" name="login" value="<%=inituser%>"> <br><br>
        <span style="color: gold"><%=password%></span>&nbsp;&nbsp;<input type="text" name="password"> <br><br>
        <!--<input class="logBtn" type="submit" value="<%=login%>"><br><br>-->
        <button class="logBtn" type="submit" formaction="http://localhost:8080/TPO3/credentials"><%=login%></button><br><br>
        <!--<input class="logBtn" type="submit" value="<%=sign%>"><br><br><br>-->
        <button class="logBtn" type="submit" formaction="http://localhost:8080/TPO3/signin.jsp"><%=sign%></button><br><br><br>
        <br><label style="color: white">Copyright to s1832<br><br>Tomasz Zajkowski</label><br><br>
    </form>
</div>
<label class="container">Aby przetestowac funkcje administratora dane logowania to admin/admin
    <br>Sprawdź rownież jak działa lokalizacja aplikacji :)
</label>
</body>
</html>
