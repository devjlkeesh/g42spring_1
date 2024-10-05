package dev.jlkeesh.javabase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyApplicationConfiguration.class);
        System.out.println(context.getBean(Student.class));
        Group group = context.getBean(Group.class);
        System.out.println(System.identityHashCode(group));
        System.out.println(group);
        System.out.println(group.student);
        context.close();
    }
}
