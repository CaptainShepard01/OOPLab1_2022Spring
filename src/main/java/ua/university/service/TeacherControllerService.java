package ua.university.service;

import ua.university.DAO.FacultyDAO;
import ua.university.models.Course;
import ua.university.models.Student;
import ua.university.models.Teacher;
import ua.university.models.Teacher;
import ua.university.utils.Utils;

import java.sql.SQLException;
import java.util.List;

public class TeacherControllerService implements ControllerService{
    private FacultyDAO facultyDAO;

    public TeacherControllerService() throws SQLException, ClassNotFoundException {
        this.facultyDAO = new FacultyDAO();
    }

    @Override
    public StringBuilder showAll() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<h2>Welcome to Teachers table</h2>\n");

        Teacher[] teachers = this.facultyDAO.indexTeacher().toArray(new Teacher[0]);
        stringBuilder.append(Utils.getTable(teachers));

        return stringBuilder;
    }

    @Override
    public StringBuilder showSingle(long id) {
        StringBuilder stringBuilder = new StringBuilder();

        Teacher teacher = this.facultyDAO.getTeacher(id);

        stringBuilder.append(Utils.getSingleModelView(teacher));

        return stringBuilder;
    }

    @Override
    public void onAdd(String[] params) {
        String name = params[0];
        this.facultyDAO.saveTeacher(new Teacher(-1, name));
    }

    @Override
    public void onDelete(long id) {
        this.facultyDAO.deleteTeacher(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return this.facultyDAO.indexStudent();
    }

    @Override
    public List<Course> getAllCourses() {
        return this.facultyDAO.indexCourse();
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return this.facultyDAO.indexTeacher();
    }
}
