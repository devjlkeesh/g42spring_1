package dev.jlkeesh.qualify;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("qualify.xml");
        PostController postController = context.getBean(PostController.class);
        System.out.println(System.identityHashCode(postController));
        PostService postService = context.getBean("grpcPostService",PostService.class);
        System.out.println(System.identityHashCode(postService));
        System.out.println(postService);
        System.out.println(context.getBean(SessionFactoryConfig.class).url);
    }
}
