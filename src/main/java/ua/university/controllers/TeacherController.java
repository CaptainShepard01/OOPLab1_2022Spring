package ua.university.controllers;

import ua.university.DAO.FacultyDAO;
import ua.university.models.Teacher;
import ua.university.service.ControllerService;
import ua.university.service.CourseControllerService;
import ua.university.service.TeacherControllerService;
import ua.university.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/teachers")
public class TeacherController extends HttpServlet {
    private ControllerService service;

    @Override
    public void init() {
        try {
            this.service = new TeacherControllerService();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();

        if (request.getParameter("id") == null) {
            stringBuilder.append(this.service.showAll());
        } else {
            long teacher_id = Long.parseLong(request.getParameter("id"));
            stringBuilder.append(service.showSingle(teacher_id));

            request.setAttribute("delete_id", teacher_id);
        }

        request.setAttribute("objectName", "Teacher");

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
                resp.sendRedirect("/teachers");
                break;
            }
            case "POST": {
                String name = req.getParameter("name");
                String[] params = new String[]{name};
                this.service.onAdd(params);
                resp.sendRedirect("/teachers");
                break;
            }
            default: {
                System.out.println("Not implemented action!");
            }
        }
        session.invalidate();
    }
}