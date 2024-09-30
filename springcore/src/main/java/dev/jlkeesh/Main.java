package dev.jlkeesh;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext();
        context.setConfigLocation("classpath:config.xml");
        context.refresh();
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
        UserRepository userRepository = context.getBean(UserRepository.class);
//        UserRepository userRepository = (UserRepository) context.getBean("userRepository");
//        UserRepository userRepository =  context.getBean("userRepository", UserRepository.class);
        System.out.println("userRepository.getUser() = " + userRepository.getUser());
          /*  for (String beanDefinitionName : context.getBeanDefinitionNames()) {
                System.out.println("beanDefinitionName = " + beanDefinitionName);
            }*/
        System.out.println("----------");
        UserService userService = context.getBean(UserService.class);
        System.out.println("userService.go() = " + userService.go());
        context.close();

    }
}