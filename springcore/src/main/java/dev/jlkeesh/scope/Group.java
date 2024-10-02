package dev.jlkeesh.scope;

public class Group {
    public Student student;

    public Group() {
        System.out.println("default constructor for Group bean " + System.identityHashCode(this));
    }

    public Group(Student student) {
        this.student = student;
    }

    public void setStudent(Student student) {
        System.out.println("this is a setter based injection " + System.identityHashCode(student));
        this.student = student;
    }
}
