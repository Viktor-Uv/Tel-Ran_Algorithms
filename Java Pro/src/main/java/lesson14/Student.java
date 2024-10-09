package lesson14;

public class Student {
    private String name;
    private int year; // курс 1, 2, 3, 4
    private String group;

    public Student(String name, int year, String group) {
        this.name = name;
        this.year = year;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }
}
