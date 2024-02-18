package center.unit.letter.shared.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("kakao")
public class KakaoProperties {

    private String clientKey;
    private String redirectUri;
}
