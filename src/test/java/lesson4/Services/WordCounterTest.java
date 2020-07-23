package lesson4.Services;

import lesson4.Models.Word;
import lesson4.Services.WordCounter;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest
public class WordCounterTest {

    @Autowired
    WordCounter wordCounter;

    @ParameterizedTest
    @MethodSource("testCountData")
    public void testCount(List<String> words, Map<String, Long> expectedCount) {
        List<Word> countedWords =  wordCounter.count(words);
        Map<String, Long> countedWordsData = new HashMap<>();

        for(Word word : countedWords) {
            countedWordsData.put(word.getWord(), word.getCount());
        }

        Assert.assertEquals(expectedCount, countedWordsData);
    }

    public static Stream<Arguments> testCountData() {
        return Stream.of(
                Arguments.of(
                        Arrays.asList("Слово", "слово", "длинное"),
                        new HashMap<String, Long>() {{
                            put("слово", 2L);
                            put("длинное", 1L);
                        }}
                )
        );
    }
}
