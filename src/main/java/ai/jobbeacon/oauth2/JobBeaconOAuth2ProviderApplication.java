package ai.jobbeacon.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JobBeaconOAuth2ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobBeaconOAuth2ProviderApplication.class, args);
    }
}
