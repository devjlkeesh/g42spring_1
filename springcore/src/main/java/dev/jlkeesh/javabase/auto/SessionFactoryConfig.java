package dev.jlkeesh.javabase.auto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:settings.properties")
public class SessionFactoryConfig {

    public final String url;

    /*public SessionFactoryConfig(@Value("${datasource.url:12}")String url) {
        this.url = url;
    }*/

    public SessionFactoryConfig(Environment env) {
        this.url = env.getRequiredProperty("datasource.url");
    }

    @Override
    public String toString() {
        return "SessionFactoryConfig{" +
                "url='" + url + '\'' +
                '}';
    }
}
