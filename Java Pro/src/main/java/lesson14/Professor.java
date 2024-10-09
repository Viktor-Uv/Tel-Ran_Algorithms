package lesson14;

import java.util.List;

public class Professor {
    private String name;
    private String subject;
    private List<Student> students;

    public Professor(String name, String subject, List<Student> students) {
        this.name = name;
        this.subject = subject;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return getName();
    }
}
