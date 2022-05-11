package ua.university.service;

import ua.university.models.*;
import ua.university.utils.Utils;

import java.util.List;

public interface ControllerService {
    StringBuilder showAll();
    StringBuilder showAll(String name, Utils.Role role);
    StringBuilder showSingle(long id);
    void onAdd(String[] params);
    void onDelete(long id);
    void onUpdate(String[] params);
    List<Student> getAllStudents();
    List<Course> getAllCourses();
    List<Teacher> getAllTeachers();

    StudentCourseRelation getStudentCourseRelation(long student_course_id);
    List<Student> getStudent(String name);
    List<Course> getCoursesByTeacher(String name);
    Course getCourseById(long id);
}
