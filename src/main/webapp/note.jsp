<%@ page import="denis.model.Note" %><%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 30/03/2022
  Time: 08:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Note editor</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/note.css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <%
        Note note = (Note) request.getAttribute("note");
        String name = note.getName();
        String content = note.getContent();
        String summary = note.getSummary();
    %>
    <form action="notes.html" method="post">
        <div id="name-parent">
            <label>
                <input id="name-area" type="text" name="name" size="50" value=<%= name %>>
            </label>
        </div>
        <div id="content-parent">
            <label>
                <textarea id="content-area" name="content" rows="10" cols="100"><%=content%></textarea>
            </label>
        </div>
        <div id="summary-parent">
            <label>
                <textarea id="summary-area" name="summary" rows="4" cols="50"><%= summary %></textarea>
            </label>
        </div>
        <div id="button-parent">
            <button id="submit-button" type="submit" class="btn btn-secondary" name="save" value=<%= name %>>
                Submit
            </button>
        </div>
    </form>
    <form action="notes.html">
        <div id="back-to-index-parent">
            <button id="back-to-index" type="submit" class="btn btn-secondary">Back to Index</button>
        </div>
    </form>
</body>
</html>

