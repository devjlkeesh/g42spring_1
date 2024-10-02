package dev.jlkeesh.javabase.auto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class Group {

    /*@Autowired  */
    private final Student student;

    @Autowired
    public Group(@Lazy Student student) {
        this.student = student;
    }

    public Group(Student student, Object s) {
        this.student = student;
    }

    /*
    @Autowired
    public void setStudent(Student student) {
        this.student = student;
    }*/

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Group{" +
                "student=" + student +
                '}';
    }
}
