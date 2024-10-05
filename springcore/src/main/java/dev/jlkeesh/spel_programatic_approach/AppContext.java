package dev.jlkeesh.spel_programatic_approach;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppContext {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("dev.jlkeesh.spel_programatic_approach");
        MYGovClient client = context.getBean(MYGovClient.class);
        System.out.println("client = " + client);

    }
}
