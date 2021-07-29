<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="./style.css"/>
</head>
<body>
<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
    }
    String usr = (String) session.getAttribute("username");
    ResourceBundle msg = (ResourceBundle) session.getAttribute("resourceBundle");
    String hello = msg.getString("welcome");
%>
<div style="text-align: center; background-color: aqua">
    <h1><%=hello + usr%>
        <button class="logBtn" style="text-align: right" onclick="location.href='http://localhost:8080/TPO3/logout'">
            Logout
        </button>
    </h1>
</div>
<div style="display: flex">
    <div style="background-image: url('bookstore.jpg'); height: 799px; width: 1200px">
        <div style="background-color: cornsilk; margin-left: 20px; margin-bottom: 20px; margin-top: 20px">
            <%
                if(request.getAttribute("books")!=null){
                    String booklist = (String) request.getAttribute("books");
                    request.removeAttribute("books");
                    out.println(booklist);
                }
            %>
        </div>
    </div>
    <div style="padding-left: 10px">
        <button class="logBtn" style="text-align: right"
                onclick="location.href='http://localhost:8080/TPO3/search'">
            Wyszukaj
        </button><br>
        <button class="logBtn" style="text-align: right"
                onclick="location.href='http://localhost:8080/TPO3/logout'">
            Dodaj okładkę
        </button><br>
        <button class="logBtn" style="text-align: right"
                onclick="location.href='http://localhost:8080/TPO3/logout'">
            Dodaj książkę
        </button><br>
        <button class="logBtn" style="text-align: right"
                onclick="location.href='http://localhost:8080/TPO3/accDelete'">
            Usuń konto
        </button><br>
    </div>
</div>
</body>
</html>
