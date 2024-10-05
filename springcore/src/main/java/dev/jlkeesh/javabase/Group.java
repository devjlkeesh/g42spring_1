package dev.jlkeesh.javabase;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

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


    @PostConstruct
    public void init() {
        System.out.println("init method for Group bean " + System.identityHashCode(this));
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy method for Group bean " + System.identityHashCode(this));
    }
}
