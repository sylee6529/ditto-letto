package center.unit.letter.presentation.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    @GetMapping({"/", "/health-check"})
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.status(200).body("ok");
    }
}
