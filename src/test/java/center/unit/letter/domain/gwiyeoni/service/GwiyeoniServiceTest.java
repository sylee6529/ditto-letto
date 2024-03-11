package center.unit.letter.domain.gwiyeoni.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@SpringBootTest
class GwiyeoniServiceTest {

    @Autowired
    private GwiyeoniService gwiyeoniService;

    @Test
    void 글씨가_전체_똑같은_경우_다_다른_결과가_출력된다() {
        String text = "가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가가";
        String gwiyeoniText = gwiyeoniService.convertToGwiyeoniText(text);

        System.out.println(gwiyeoniText);
    }

    @Test
    void 난가끔눈물을흘린다() {
        String text = """
                난... 가끔...
                눈물을 흘린다....
                가끔은 눈물을 참을 수 없는 내가 별루다...
                맘이 아파서....
                소리치며... 울 수 있다는 건....
                좋은 거야.....
                머... 꼭 슬퍼야만 우는 건 아니잖아...^^
                난... 눈물이 ....좋다.....
                아니...
                머리가 아닌.....
                맘으로.....우는 내가 좋다.....
                """;
        String gwiyeoniText = gwiyeoniService.convertToGwiyeoniText(text);

        System.out.println(gwiyeoniText);
    }

    @Test
    void 없는단어를넣었을때그대로출력된다() {
        String text = "없";
        String gwiyeoniText = gwiyeoniService.convertToGwiyeoniText(text);
        assertAll(() -> assertEquals(text, gwiyeoniText));
    }
}