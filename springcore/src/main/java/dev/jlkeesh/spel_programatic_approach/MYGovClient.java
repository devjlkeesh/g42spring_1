package dev.jlkeesh.spel_programatic_approach;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:settings.properties")
public class MYGovClient {

    @Value("${mygov.url}")
    private String myGovUrl;

    @Value("${mygov.username}")
    private String myGovUsername;

    @Value("${mygov.password}")
    private String myGovPassword;

    @Override
    public String toString() {
        return "MYGovClient{" +
                "myGovUrl='" + myGovUrl + '\'' +
                ", myGovUsername='" + myGovUsername + '\'' +
                ", myGovPassword='" + myGovPassword + '\'' +
                '}';
    }
}
