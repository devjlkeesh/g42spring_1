package dev.jlkeesh.javabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyApplicationConfiguration {

    @Bean
    public Group group(Student student) {
        return new Group(student);
    }

    @Bean
    @Scope("prototype")
    public Student student() {
        return new Student();
    }
}
