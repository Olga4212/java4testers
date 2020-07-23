package lesson4.Services;

import lesson4.Models.Word;
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
public class WordExtractorTest {

    @Autowired
    WordExtractor wordExtractor;

    @ParameterizedTest
    @MethodSource("testExtractWordsData")
    public void testExtractWords(String book, List<String> expectedWords) {
        List<String> extractedWords = wordExtractor.extractWords(book);
        Assert.assertEquals(expectedWords, extractedWords);
    }

    public static Stream<Arguments> testExtractWordsData() {
        return Stream.of(
                Arguments.of(
                        "\"Hello, Jessica!\" the (old) woman said. Her.",
                        Arrays.asList("Hello", "Jessica", "the", "old", "woman", "said", "Her")
                ),
                Arguments.of(
                        "!! -hmm... \"Nope\"!?!",
                        Arrays.asList("hmm", "Nope")
                )
        );
    }
}
