<%@ page import="ua.university.models.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if (request.getAttribute("objectName") == "Course" && request.getAttribute("delete_id") == null) { %>
<form action="/courses" method="post">
    <br>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" required/></td>
        </tr>
        <tr>
            <td>MaxGrade:</td>
            <td><input type="text" name="maxGrade" required/></td>
        </tr>
        <tr>
            <td>Select Teacher:</td>
            <td>
                <select name="teacher_id">
                    <% for (Teacher teacher: (List<Teacher>) request.getAttribute("teacherList")) { %>
                    <option value="<%=teacher.getId()%>"> <%=teacher.getName().toString()%> </option>
                    <% } %>
                </select>
            </td>
        </tr>
    </table>
    <%  session.setAttribute("action", "POST"); %>
    <input type="submit" value="Add!" />
</form>
<% } %>