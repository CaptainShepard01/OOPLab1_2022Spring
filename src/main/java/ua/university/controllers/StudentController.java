package ua.university.controllers;

import ua.university.DAO.FacultyDAO;
import ua.university.models.Course;
import ua.university.models.Student;
import ua.university.models.Teacher;
import ua.university.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/students")
public class StudentController extends HttpServlet {
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
            stringBuilder.append("<h2>Welcome to Students table</h2>\n");

            Student[] students = this.facultyDAO.indexStudent().toArray(new Student[0]);
            stringBuilder.append(Utils.getTable(students));
        } else {
            long student_id = Long.parseLong(request.getParameter("id"));
            Student student = this.facultyDAO.getStudent(student_id);

            request.setAttribute("delete_id", student_id);

            stringBuilder.append(Utils.getViewPage(student));
        }

        request.setAttribute("objectName", "Student");

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
                this.facultyDAO.deleteStudent(Long.parseLong(session.getAttribute("delete_id").toString()));
                resp.sendRedirect("/students");
                break;
            }
            case "POST": {
                String name = req.getParameter("name");
                this.facultyDAO.saveStudent(new Student(-1, name));
                resp.sendRedirect("/students");
                break;
            }
            default: {
                System.out.println("Not implemented action!");
            }
        }
        session.invalidate();
    }
}
