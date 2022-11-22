<%@ page import="denis.model.Note" %>
<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 28/03/2022
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.ArrayList" %>
<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <%!
        int currentIndex;
    %>
    <div class="list-group">
    <%
        ArrayList<Note> notes = (ArrayList<Note>) request.getAttribute("notes");
        for (currentIndex = 0 ; currentIndex < notes.size() ; currentIndex++) {
            String noteURL = "/note" + "?name=" + notes.get(currentIndex).getName();
    %>
            <a href=<%= noteURL %> " class="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
                <div class="d-flex gap-2 w-100 justify-content-between">
                    <div>
                        <h6 class="mb-0"><%= notes.get(currentIndex).getName() %></h6>
                        <p class="mb-0 opacity-75"><%= notes.get(currentIndex).getSummary() %></p>
                    </div>
                    <small class="opacity-50 text-nowrap"><%= notes.get(currentIndex).getDateAdded() %></small>
                    <form action="notes.html" method="post">
                        <button type="submit" class="btn btn-danger" name="button"
                                value=<%= notes.get(currentIndex).getName() %>>
                            Delete
                        </button>
                    </form>
                </div>
            </a>
        <%}%>
        <jsp:include page="indexFooter.jsp" />
    </div>
</body>
</html>
