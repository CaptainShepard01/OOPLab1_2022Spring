package ua.university.service;

import ua.university.models.Course;
import ua.university.models.Student;
import ua.university.models.Teacher;

import java.util.List;

public interface ControllerService {
    StringBuilder showAll();
    StringBuilder showSingle(long id);
    void onAdd(String[] params);
    void onDelete(long id);
    List<Student> getAllStudents();
    List<Course> getAllCourses();
    List<Teacher> getAllTeachers();
}
