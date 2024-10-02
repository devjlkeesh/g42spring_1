package dev.jlkeesh.javabase.auto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class Student {
    private final Group group;

    public Student(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "student bean " + System.identityHashCode(this);
    }
}
