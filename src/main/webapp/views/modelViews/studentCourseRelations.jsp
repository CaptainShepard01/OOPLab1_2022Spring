<%@ page import="java.util.List" %>
<%@ page import="ua.university.models.Student" %>
<%@ page import="ua.university.models.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    if (request.getAttribute("objectName") == "StudentCourseRelation" && rolesSet != null && (rolesSet.contains("student") || rolesSet.contains("teacher"))) {
%>
    <% if (request.getAttribute("delete_id") == null) { %>
        <form action="/studentCourseRelations" method="post">
            <div class="form-group">
                <label for="studentField">Select student</label>
                <select name="student_id" id="studentField" class="form-control" required>
                    <% for (Student student : (List<Student>) request.getAttribute("studentList")) { %>
                    <option value="<%=student.getId()%>"><%=student.getName().toString()%>
                    </option>
                    <% } %>
                </select>
            </div>
            <div class="form-group">
                <label for="courseField">Select course</label>
                <select name="course_id" id="courseField" class="form-control" required>
                    <% for (Course course : (List<Course>) request.getAttribute("courseList")) { %>
                    <option value="<%=course.getId()%>"><%=course.getName().toString() + ", Max grade: " + course.getMaxGrade()%>
                    </option>
                    <% } %>
                </select>
            </div>
            <% if (rolesSet.contains("teacher")) { %>
                <div class="form-group">
                    <label for="gradeField">Grade</label>
                    <input type="number" name="grade" class="form-control" id="gradeField" placeholder="Enter grade" required
                           min="1" max="30"/>
                </div>
                <div class="form-group">
                    <label for="reviewField">Review</label>
                    <textarea type="text" rows="5" cols="40" name="review" class="form-control" id="reviewField"
                              placeholder="Enter review" required></textarea>
                </div>
            <% } %>
            <% session.setAttribute("action", "POST"); %>
            <input type="submit" class="btn btn-primary" value="Add!"/>
        </form>
    <% } %>

    <% if (request.getAttribute("delete_id") != null) { %>
        <form action="/studentCourseRelations" method="post">
            <% session.setAttribute("action", "DELETE"); %>
            <% session.setAttribute("delete_id", request.getAttribute("delete_id")); %>
            <input type="submit" class="btn btn-danger" value="Delete"/>
        </form>
    <% } %>
<% } %>