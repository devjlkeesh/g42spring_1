package dev.jlkeesh.scope;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("scope.xml");
   /*     Student student = context.getBean("student", Student.class);
        System.out.println(System.identityHashCode(student));
        System.out.println(System.identityHashCode(student.group));*/
/*
        Student student2 = context.getBean("student", Student.class);
        System.out.println(System.identityHashCode(student2));
        System.out.println(System.identityHashCode(student2.group));
        System.out.println(System.identityHashCode(context.getBean(Group.class)));
        System.out.println(System.identityHashCode(context.getBean(Group.class)));
        System.out.println(System.identityHashCode(context.getBean(Group.class)));
        System.out.println(System.identityHashCode(context.getBean(Group.class)));*/

        Group g1 = context.getBean(Group.class);
        System.out.println(System.identityHashCode(g1));
        System.out.println(System.identityHashCode(g1.student));
        Group g2 = context.getBean(Group.class);
        System.out.println(System.identityHashCode(g2));
        System.out.println(System.identityHashCode(g2.student));
        context.close();

    }
}
