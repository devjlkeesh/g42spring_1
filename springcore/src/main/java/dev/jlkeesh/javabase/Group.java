package dev.jlkeesh.javabase;


public class Group {
    public final Student student;

    public Group(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Group{" +
                "student=" + student +
                '}';
    }
}
