package ua.university.models;

public class Course {
    private long id;
    private String name;
    private int maxGrade;

    private Teacher teacher;

    public Course() {
    }

    public Course(long id, String name, int maxGrade, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.maxGrade = maxGrade;
        this.teacher = teacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(int maxGrade) {
        this.maxGrade = maxGrade;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxGrade=" + maxGrade +
                ", teacher=" + teacher +
                '}';
    }
}
