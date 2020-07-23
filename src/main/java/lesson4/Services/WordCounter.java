package lesson4.Services;

import lesson4.Models.Word;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;

@Service
public class WordCounter {

    public List<Word> count(List<String> words) {
        Map<String, Long> wordCounts = new HashMap<>();

        for(String word : words) {
            String wordLower = word.toLowerCase();
            wordCounts.put(wordLower, wordCounts.getOrDefault(wordLower, 0L) + 1L);
        }

        return toList(wordCounts);
    }

    private List<Word> toList(Map<String, Long> stringLongMap) {
        List<Word> words = new ArrayList<>();
        for (Map.Entry<String, Long> stringLongEntry : stringLongMap.entrySet()) {
            words.add(new Word(stringLongEntry.getKey(), stringLongEntry.getValue()));
        }
        return words;
    }

}