package ua.university.controllers;

import ua.university.DAO.FacultyDAO;
import ua.university.models.Course;
import ua.university.models.Student;
import ua.university.models.StudentCourseRelation;
import ua.university.models.Teacher;
import ua.university.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/studentCourseRelations")
public class StudentCourseRelationController extends HttpServlet {
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
        StringBuilder stringBuilder = new StringBuilder();

        if (request.getParameter("id") == null) {
            stringBuilder.append("<h2>Welcome to Student-Course relations table</h2>\n");

            StudentCourseRelation[] studentCourseRelations = this.facultyDAO.indexStudentCourseRelation().toArray(new StudentCourseRelation[0]);
            stringBuilder.append(Utils.getTable(studentCourseRelations));
        } else {
            long scr_id = Long.parseLong(request.getParameter("id"));
            StudentCourseRelation studentCourseRelation = this.facultyDAO.getStudentCourseRelation(scr_id);

            request.setAttribute("delete_id", scr_id);

            stringBuilder.append(Utils.getViewPage(studentCourseRelation));
        }

        request.setAttribute("studentList", this.facultyDAO.indexStudent());
        request.setAttribute("courseList", this.facultyDAO.indexCourse());

        request.setAttribute("objectName", "StudentCourseRelation");

        request.setAttribute("info", stringBuilder);
        request.setAttribute("action", "None");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}