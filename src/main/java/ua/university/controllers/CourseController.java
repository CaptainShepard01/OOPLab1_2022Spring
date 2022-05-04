package ua.university.controllers;

import ua.university.DAO.FacultyDAO;
import ua.university.models.Course;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/courses")
public class CourseController extends HttpServlet {
    private FacultyDAO facultyDAO;

    @Override
    public void init()  {
        try {
            this.facultyDAO = new FacultyDAO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder stringBuilder = new StringBuilder();

        if (request.getParameter("id") == null) {
            stringBuilder.append("<h2>Welcome to Courses table</h2>\n");

            Course[] courses = this.facultyDAO.indexCourse().toArray(new Course[0]);
            stringBuilder.append(Utils.getTable(courses));
        } else {
            long course_id = Long.parseLong(request.getParameter("id"));
            Course course = this.facultyDAO.getCourse(course_id);

            request.setAttribute("delete_id", course_id);

            stringBuilder.append("<h4>Course ID</h4>\n")
                    .append("<p>").append(course.getId()).append("</p>")
                    .append("<h4>Course Name</h4>\n")
                    .append("<p>").append(course.getName()).append("</p>")
                    .append("<h4>Course Max grade</h4>\n")
                    .append("<p>").append(course.getMaxGrade()).append("</p>")
                    .append("<h4>Course Teacher</h4>\n")
                    .append("<p>").append(course.getTeacher().toString()).append("</p>")
                    .append("<br>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
