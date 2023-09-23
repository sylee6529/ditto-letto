package center.unit.letter.infrastructure.cyworld;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CyworldService {

    private final CyworldClient cyworldClient;

    public String execute(String text) {
        String cyworldText = cyworldClient.convert(
                "black",
                text
        ).split("\\|\\^!#!\\^\\|")[1]
                .replace("<br />", "\n");

        return cyworldText.trim();
    }
}
