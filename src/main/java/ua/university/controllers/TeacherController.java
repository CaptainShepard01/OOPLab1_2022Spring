package ua.university.controllers;

import ua.university.DAO.FacultyDAO;
import ua.university.models.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/teachers")
public class TeacherController extends HttpServlet {
    private FacultyDAO facultyDAO;

    @Override
    public void init() {
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
        try (PrintWriter writer = response.getWriter()) {
            Teacher[] teachers = this.facultyDAO.indexTeacher().toArray(new Teacher[0]);

            writer.println("<h2>Welcome to Teachers table</h2>");
            for (Teacher teacher : teachers) {
                writer.println(teacher);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}