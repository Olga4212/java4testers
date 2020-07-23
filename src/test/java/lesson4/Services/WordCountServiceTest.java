package lesson4.Services;

import lesson4.Models.Word;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

@SpringBootTest
public class WordCountServiceTest {

    @Autowired
    WordCountService service;

    @ParameterizedTest
    @MethodSource("testCountWordsData")
    public void testCountWordsData(String book, List<String> expectedWords, List<Long> expectedCounts) {
        List<Word> countedWords = service.countWords(book);

        for(int i=0; i<countedWords.size(); i++) {
            Assert.assertEquals(expectedWords.get(i), countedWords.get(i).getWord());
            Assert.assertEquals(expectedCounts.get(i), countedWords.get(i).getCount());
        }
    }

    public static Stream<Arguments> testCountWordsData() {
        return Stream.of(
                Arguments.of(
                        "Длинное длинное слово",
                        Arrays.asList("длинное", "слово"),
                        Arrays.asList(2L, 1L)
                ),
                Arguments.of(
                        "Слово длинное Длинное",
                        Arrays.asList("длинное", "слово"),
                        Arrays.asList(2L, 1L)
                )
        );
    }

    @Test
    public void testCountWordsOnDune() {
        List<String> bookLines = new ArrayList<>();

        try (BufferedReader fileToBeRead = new BufferedReader(new FileReader("src/test/resources/dune.txt"))) {
            String line;
            while ( (line = fileToBeRead.readLine()) != null) {
                bookLines.add(line);
            }

        } catch (IOException e) {
            Assert.fail();
        }

        String book = String.join("\n", bookLines);
        List<Word> countedWords = service.countWords(book);

        for (Word word : countedWords) {
            System.out.println(word.getWord() + " " + word.getCount());
        }
    }
}
