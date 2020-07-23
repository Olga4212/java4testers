package lesson4.Services;

import lesson4.Models.Word;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class WordComparatorTest {

    @Autowired
    WordComparator wordComparator;

    @ParameterizedTest
    @MethodSource("testCompareData")
    public void testCompare(Word word1, Word word2, int expectedResult) {
        Assert.assertEquals(expectedResult, wordComparator.compare(word1, word2));
    }

    public static Stream<Arguments> testCompareData() {
        return Stream.of(
                Arguments.of(
                        new Word("Word", 1L),
                        new Word("Word", 2L),
                        -1
                ),
                Arguments.of(
                        new Word("Word", 1L),
                        new Word("Word", 1L),
                        0
                ),
                Arguments.of(
                        new Word("Word", 3L),
                        new Word("Word", 2L),
                        1
                )
        );
    }
}
