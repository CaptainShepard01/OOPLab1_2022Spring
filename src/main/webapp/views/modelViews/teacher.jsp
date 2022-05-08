<%@ page import="ua.university.models.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if (request.getAttribute("objectName") == "Teacher" && request.getAttribute("delete_id") == null) { %>
<form action="/teachers" method="post">
    <div class="form-group">
        <label for="nameField">Name</label>
        <input type="text" name="name" class="form-control" id="nameField" placeholder="Enter name" required>
    </div>
    <%  session.setAttribute("action", "POST"); %>
    <input type="submit" class="btn btn-primary" value="Add!" />
</form>
<% } %>

<% if (request.getAttribute("objectName") == "Teacher" && request.getAttribute("delete_id") != null) { %>
<form action="/teachers" method="post">
    <% session.setAttribute("action", "DELETE"); %>
    <% session.setAttribute("delete_id", request.getAttribute("delete_id")); %>
    <input type="submit" class="btn btn-danger" value="Delete" />
</form>
<% } %>