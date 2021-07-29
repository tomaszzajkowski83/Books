<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Biblioteka PJATKowego człowieka</title>
    <link rel="stylesheet" href="./style.css"/>
</head>
<body>
<%
    ResourceBundle msg = (ResourceBundle) session.getAttribute("resourceBundle");
    String username = msg.getString("username");
    String password = msg.getString("password");
    String login = msg.getString("sign");
    String hello = msg.getString("register");
%>
<h1><b>Biblioteka PJATKowego Człowieka</b></h1>
<hr>
<div style="text-align: center; background-color: aqua"><h2><%=hello%></h2></div>
<hr>
<div style="text-align: center; background-image: url('pjatk.png')">
    <form method="post" action="http://localhost:8080/TPO3/accCreate"><br><br>
        <span style="color: gold"><%=username%></span>&nbsp;&nbsp;<input type="text" name="login"> <br><br>
        <span style="color: gold"><%=password%></span>&nbsp;&nbsp;<input type="text" name="password"> <br><br>
        <input class="logBtn" type="submit" name="creation" value="<%=login%>"><br><br><br><br><br>
        <br><label style="color: white">Copyright to s1832<br><br>Tomasz Zajkowski</label><br><br>
    </form>
</div>
<label class="container">Aby przetestowac funkcje administratora dane logowania to admin/admin
    <br>Sprawdź rownież jak działa lokalizacja aplikacji :)
</label>
</body>
</html>
