package lesson4.Services;

import lesson4.Models.Word;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Строка (книга)
 * Посчитать в ней вхождение уникальных слов
 * Отсортировать слова по популярности вхожения
 * Вернуть в ответе
 */
@Service
public class WordCountService {

    private WordExtractor wordExtractor;
    private WordCounter wordCounter;

    public WordCountService(WordExtractor wordExtractor, WordCounter wordCounter) {
        this.wordExtractor = wordExtractor;
        this.wordCounter = wordCounter;
    }

    public List<Word> countWords(String book) {
        List<String> strings = wordExtractor.extractWords(book);
        List<Word> count = wordCounter.count(strings);

        count.sort(new WordComparator().reversed());

        return count;
    }

}