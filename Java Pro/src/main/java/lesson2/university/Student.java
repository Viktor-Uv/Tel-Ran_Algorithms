package lesson2.university;

public class Student extends Person {
    private int year;
    private String program;

    public Student(String name, int age, int year, String program) {
        super(name, age);
        this.year = year;
        this.program = program;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    // переопределите introduce
    // чтобы он выдавал имя, возраст, программу и год
    @Override
    public void introduce() {
        System.out.println(
                "I'm a Student, Name: " + getName() +
                ", Program: " + getProgram() +
                ", Year: " + getYear()
        );
    }
}
