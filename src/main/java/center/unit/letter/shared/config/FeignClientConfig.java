package center.unit.letter.shared.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "center.unit.letter.infrastructure")
public class FeignClientConfig {
}