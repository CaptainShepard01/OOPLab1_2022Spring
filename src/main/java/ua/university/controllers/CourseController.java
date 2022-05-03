package ua.university.controllers;

import ua.university.DAO.FacultyDAO;
import ua.university.models.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        response.setContentType("text/html");
        System.out.println("courses doGet");
        try (PrintWriter writer = response.getWriter()) {
            Course[] courses = this.facultyDAO.indexCourse().toArray(new Course[0]);

            writer.println("<h2>Welcome to Courses table</h2>");
            for (Course course : courses) {
                writer.println(course);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
