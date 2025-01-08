package ai.jobbeacon.oauth2;

import ai.jobbeacon.oauth2.config.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
public class JobBeaconOAuth2ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobBeaconOAuth2ProviderApplication.class, args);
    }
}
