package ua.university.controllers;

import ua.university.service.ControllerService;
import ua.university.service.StudentCourseRelationControllerService;
import ua.university.utils.KeycloakTokenUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/studentCourseRelations")
public class StudentCourseRelationController extends HttpServlet {
    private ControllerService service;

    @Override
    public void init() {
        try {
            this.service = new StudentCourseRelationControllerService();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("username", KeycloakTokenUtil.getPreferredUsername(request));
        request.setAttribute("roles", KeycloakTokenUtil.getRoles(request));

        StringBuilder stringBuilder = new StringBuilder();

        if (request.getParameter("id") == null) {
            stringBuilder.append(service.showAll());
        } else {
            long student_course_id = Long.parseLong(request.getParameter("id"));
            stringBuilder.append(service.showSingle(student_course_id));

            request.setAttribute("delete_id", student_course_id);
        }

        request.setAttribute("studentList", this.service.getAllStudents());
        request.setAttribute("courseList", this.service.getAllCourses());

        request.setAttribute("objectName", "StudentCourseRelation");

        request.setAttribute("info", stringBuilder);
        request.setAttribute("action", "None");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = session.getAttribute("action").toString();
        switch (action) {
            case "DELETE": {
                long delete_id = Long.parseLong(session.getAttribute("delete_id").toString());
                this.service.onDelete(delete_id);
                resp.sendRedirect("/studentCourseRelations");
                break;
            }
            case "POST": {
                String studentId = req.getParameter("student_id");
                String courseId = req.getParameter("course_id");
                String grade = req.getParameter("grade");
                String review = req.getParameter("review");
                String[] params = new String[]{studentId, courseId, grade, review};
                this.service.onAdd(params);
                resp.sendRedirect("/studentCourseRelations");
                break;
            }
            default: {
                System.out.println("Not implemented action!");
            }
        }
        session.invalidate();
    }
}