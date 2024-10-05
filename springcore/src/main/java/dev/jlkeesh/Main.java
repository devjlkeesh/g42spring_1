package dev.jlkeesh;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        //BeanFactory beanFactory = new XmlBeanFactory("applicationContext.xml");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext();
        context.setConfigLocation("classpath:config.xml");
        context.refresh();
        System.out.println("----------");
        System.out.println(System.identityHashCode(context.getBean(UserService.class)));
        System.out.println(System.identityHashCode(context.getBean(UserService.class)));
        System.out.println(System.identityHashCode(context.getBean(UserService.class)));
        context.close();

    }
}