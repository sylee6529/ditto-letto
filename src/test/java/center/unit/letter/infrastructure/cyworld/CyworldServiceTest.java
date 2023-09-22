package center.unit.letter.infrastructure.cyworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CyworldServiceTest {

    @Autowired
    private CyworldService cyworldService;

    @Test
    void execute() {
        String text = "난... 가끔...\n" +
                "눈물을 흘린다....\n" +
                "가끔은 눈물을 참을 수 없는 내가 별루다...\n" +
                "맘이 아파서....\n" +
                "소리치며... 울 수 있다는 건....\n" +
                "좋은 거야.....\n" +
                "머... 꼭 슬퍼야만 우는 건 아니잖아...^^\n" +
                "난... 눈물이 ....좋다.....\n" +
                "아니...\n" +
                "머리가 아닌.....\n" +
                "맘으로.....우는 내가 좋다.....";
        String cyworldText = cyworldService.execute(text).replace("<br />", "\n");
        System.out.println(cyworldText);
    }
}
