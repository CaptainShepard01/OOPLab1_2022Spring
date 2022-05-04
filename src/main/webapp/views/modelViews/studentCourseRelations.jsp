<%@ page import="ua.university.models.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.university.models.Student" %>
<%@ page import="ua.university.models.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if (request.getAttribute("objectName") == "StudentCourseRelation" && request.getAttribute("delete_id") == null) { %>
<form action="/studentCourseRelations" method="post">
    <br>
    <table>
        <tr>
            <td>Select Student:</td>
            <td>
                <select name="student_id">
                    <% for (Student student: (List<Student>) request.getAttribute("studentList")) { %>
                    <option value="<%=student.getId()%>"> <%=student.getName().toString()%> </option>
                    <% } %>
                </select>
            </td>
        </tr>
        <tr>
            <td>Select Course:</td>
            <td>
                <select name="course_id">
                    <% for (Course course: (List<Course>) request.getAttribute("courseList")) { %>
                    <option value="<%=course.getId()%>"> <%=course.getName().toString()%> </option>
                    <% } %>
                </select>
            </td>
        </tr>
        <tr>
            <td>Grade:</td>
            <td><input type="text" name="grade" required/></td>
        </tr>
        <tr>
            <td>Review:</td>
            <td><textarea rows="5" cols="40" name="review"></textarea></td>
        </tr>
    </table>
    <%  session.setAttribute("action", "POST"); %>
    <input type="submit" value="Add!" />
</form>

<% } %>

<% if (request.getAttribute("objectName") == "StudentCourseRelation" && request.getAttribute("delete_id") != null) { %>
<form action="/studentCourseRelations" method="post">
    <% session.setAttribute("action", "DELETE"); %>
    <% session.setAttribute("delete_id", request.getAttribute("delete_id")); %>
    <input type="submit" value="Delete" />
</form>
<% } %>