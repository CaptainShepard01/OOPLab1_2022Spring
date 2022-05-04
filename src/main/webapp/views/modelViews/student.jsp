<%@ page import="ua.university.models.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if (request.getAttribute("objectName") == "Student" &&  request.getAttribute("delete_id") == null) { %>
<form action="/students" method="post">
    <br>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" required/></td>
        </tr>
    </table>
    <%  session.setAttribute("action", "POST"); %>
    <input type="submit" value="Add!" />
</form>
<% } %>


<% if (request.getAttribute("objectName") == "Student" &&request.getAttribute("delete_id") != null) { %>
<form action="/students" method="post">
    <% session.setAttribute("action", "DELETE"); %>
    <% session.setAttribute("delete_id", request.getAttribute("delete_id")); %>
    <input type="submit" value="Delete" />
</form>
<% } %>