package center.unit.letter.application.gwiyeoni;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class SaveAllGwiyeoniWordServiceTest {

    @Autowired
    private SaveAllGwiyeoniWordService saveAllGwiyeoniWordService;

    @Test
    void execute() {
        saveAllGwiyeoniWordService.execute();
    }
}