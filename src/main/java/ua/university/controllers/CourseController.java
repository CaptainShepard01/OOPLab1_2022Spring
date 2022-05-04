package ua.university.controllers;

import ua.university.DAO.FacultyDAO;
import ua.university.models.Course;
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
import java.sql.SQLException;

@WebServlet("/courses")
public class CourseController extends HttpServlet {
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


        request.setAttribute("info", stringBuilder);
        request.setAttribute("teacherList", this.facultyDAO.indexTeacher());
        request.setAttribute("objectName", "Course");
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
                this.facultyDAO.deleteCourse(Long.parseLong(session.getAttribute("delete_id").toString()));
                resp.sendRedirect("/courses");
                break;
            }
            case "POST": {
                String name = req.getParameter("name");
                int maxGrade = Integer.parseInt(req.getParameter("maxGrade"));
                Teacher teacher = this.facultyDAO.getTeacher(Long.parseLong(req.getParameter("teacher_id")));
                this.facultyDAO.saveCourse(new Course(-1, name, maxGrade, teacher));
                resp.sendRedirect("/courses");
                break;
            }
            default: {
                System.out.println("Not implemented action!");
            }
        }
    }
}