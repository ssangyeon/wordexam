package toy.example.wordexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.example.wordexam.domain.Word;
import toy.example.wordexam.domain.WordBook;
import toy.example.wordexam.repository.WordBookRepository;
import toy.example.wordexam.repository.WordRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;
    private final WordBookRepository wordBookRepository;
    @Transactional
    public Long makeWordBook(String name){
        WordBook wordBook = WordBook.createWordBook(name);
        wordBookRepository.save(wordBook);
        return wordBook.getId();
    }

    @Transactional
    public Long saveWord(String country, String capital, Long wordBookId){
        Word word = Word.createWord(country,capital,wordBookRepository.findOne(wordBookId));
        wordRepository.save(word);
        return word.getId();
    }

    public List<Word> findWords(){
        return wordRepository.findAll();
    }

    public Word findOne(Long wordId){
        return wordRepository.findOne(wordId);
    }

    public Long isValidWordBook(String wordBookName) {
        List<WordBook> wordBookList = wordBookRepository.findByName(wordBookName);
        if(wordBookList.isEmpty()){
            return makeWordBook(wordBookName);
        }
        return wordBookList.get(0).getId();
    }

}
