package center.unit.letter.domain.gwiyeoni.service;

import center.unit.letter.domain.gwiyeoni.Gwiyeoni;
import center.unit.letter.presentation.gwiyeoni.GwiyeoniRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class GwiyeoniService {

    private final GwiyeoniRepository gwiyeoniRepository;

    @Transactional(readOnly = true)
    public String convertToGwiyeoniText(String text) {
        List<String> textList = toCharList(text);
        List<Gwiyeoni> gwiyeoniList = gwiyeoniRepository.findByTextIn(textList);
        GwiyeoniMap gwiyeoniMap = new GwiyeoniMap(gwiyeoniList);

        return gwiyeoniMap.convertToGwiyeoniText(text);
    }


    private List<String> toCharList(String input) {
        char[] charArray = input.toCharArray();
        List<String> charList = new ArrayList<>();

        for (char c : charArray) {
            charList.add(String.valueOf(c));
        }

        return charList;
    }

}

class GwiyeoniMap {

    private final Map<String, List<String>> gwiyeoniMap;

    public GwiyeoniMap(List<Gwiyeoni> gwiyeoniList) {
        this.gwiyeoniMap = new HashMap<>();
        gwiyeoniList.forEach(g -> addGwiyeoniStringToMap(g, gwiyeoniMap));
    }

    private void addGwiyeoniStringToMap(Gwiyeoni g, Map<String, List<String>> gwiyeoniMap) {
        List<String> gwiyeoniTextList = gwiyeoniMap.get(g.getText());

        if (gwiyeoniTextList == null || gwiyeoniTextList.isEmpty()) {
            gwiyeoniMap.put(g.getText(), new ArrayList<>(List.of(g.getGwiyeoniText())));
            return;
        }

        gwiyeoniTextList.add(g.getGwiyeoniText());
    }

    public String convertToGwiyeoniText(String text) {
        char[] textArray = text.toCharArray();
        StringBuilder result = new StringBuilder();

        for (char c : textArray) {
            result.append(getGwiyeoniText(String.valueOf(c)));
        }

        return result.toString();
    }

    private String getGwiyeoniText(String text) {
        List<String> gwiyeoniTextList = gwiyeoniMap.get(text);

        if (gwiyeoniTextList == null || gwiyeoniTextList.isEmpty()) {
            return text;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(gwiyeoniTextList.size());
        return gwiyeoniTextList.get(randomIndex);
    }
}
