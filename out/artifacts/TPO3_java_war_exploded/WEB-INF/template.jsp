<%--
  Created by IntelliJ IDEA.
  User: Husar
  Date: 27.05.2021
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
    <label>Wybierz sposób wyszukiwania</label><br>
    <input type="radio" id="searchAll"
    name="searching" value="all" checked>
    <label for="searchAll">Show All</label>
    <input type="radio" id="searchTitle"
           name="searching" value="title">
    <label for="searchTitle">Title</label>
    <input type="radio" id="searchAuthor"
           name="searching" value="autor">
    <label for="searchAuthor">Author</label><br>
    <label style="padding-bottom: 5px">Wpisz wyszukiwana frazę!</label>&nbsp;&nbsp;
    <input type="text" name="patern">&nbsp;&nbsp;
    <button class="logBtn" type="submit" formaction="http://localhost:8080/TPO3/books" value="">Potwierdź</button>
</form>
</body>
</html>
