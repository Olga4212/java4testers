package lesson4.Services;

import lesson4.Models.Word;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class WordComparator implements Comparator<Word> {

    @Override
    public int compare(Word o1, Word o2) {
        return o1.getCount().compareTo(o2.getCount());
    }
}