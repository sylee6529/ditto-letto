package center.unit.letter.shared.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("apple")
public class AppleProperties {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String teamId;
}
