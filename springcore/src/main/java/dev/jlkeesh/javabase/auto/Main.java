package dev.jlkeesh.javabase.auto;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        var context = new AnnotationConfigApplicationContext("dev.jlkeesh.javabase.auto");
        var context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println(context.getBean(Student.class));
        Group group = context.getBean(Group.class);
        System.out.println(System.identityHashCode(group));
        System.out.println(group);
        System.out.println(group.getStudent());
        System.out.println(context.getBean(SessionFactoryConfig.class));
    }
}
