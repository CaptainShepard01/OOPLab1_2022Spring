package ua.university.models;

public class StudentCourseRelation {
    private long id;
    private Student student;
    private Course course;
    private int grade;
    private String review;

    public StudentCourseRelation() {
    }

    public StudentCourseRelation(long id, Student student, Course course, int grade, String review) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.grade = grade;
        this.review = review;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "StudentCourseRelation{" +
                "id=" + id +
                "course=" + course +
                ", student=" + student +
                ", grade=" + grade +
                '}';
    }
}
