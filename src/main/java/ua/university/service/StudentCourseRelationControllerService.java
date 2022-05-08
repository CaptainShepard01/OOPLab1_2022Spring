package ua.university.service;

import ua.university.DAO.FacultyDAO;
import ua.university.models.Course;
import ua.university.models.Student;
import ua.university.models.StudentCourseRelation;
import ua.university.models.Teacher;
import ua.university.utils.Utils;

import java.sql.SQLException;
import java.util.List;

public class StudentCourseRelationControllerService implements ControllerService{
    private FacultyDAO facultyDAO;

    public StudentCourseRelationControllerService() throws SQLException, ClassNotFoundException {
        this.facultyDAO = new FacultyDAO();
    }

    @Override
    public StringBuilder showAll() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<h2>Welcome to Student-Course relations table</h2>\n");

        StudentCourseRelation[] studentCourseRelations = this.facultyDAO.indexStudentCourseRelation().toArray(new StudentCourseRelation[0]);
        stringBuilder.append(Utils.getTable(studentCourseRelations));

        return stringBuilder;
    }

    @Override
    public StringBuilder showSingle(long id) {
        StringBuilder stringBuilder = new StringBuilder();

        StudentCourseRelation studentCourseRelation = this.facultyDAO.getStudentCourseRelation(id);

        stringBuilder.append(Utils.getSingleModelView(studentCourseRelation));

        return stringBuilder;
    }

    @Override
    public void onAdd(String[] params) {
        long studentId = Long.parseLong(params[0]);
        Student student = this.facultyDAO.getStudent(studentId);
        long courseId = Long.parseLong(params[1]);
        Course course = this.facultyDAO.getCourse(courseId);
        int grade = Integer.parseInt(params[2]);
        String review = params[3];
        this.facultyDAO.saveStudentCourseRelation(new StudentCourseRelation(-1, student, course, grade, review));
    }

    @Override
    public void onDelete(long id) {
        this.facultyDAO.deleteStudentCourseRelation(id);
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
