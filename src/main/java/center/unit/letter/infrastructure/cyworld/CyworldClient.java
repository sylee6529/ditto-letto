package center.unit.letter.infrastructure.cyworld;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name = "cyworld", url = "https://instablank.com/ajax/ajax_hangul_cyworld_change_test.php")
public interface CyworldClient {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String convert(
            @RequestPart(value = "change_type") String changeType,
            @RequestPart(value = "instagram_or_text") String text
    );
}