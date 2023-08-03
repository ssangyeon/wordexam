package toy.example.wordexam.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)//    protected Word() {}의 역할
public class Word {

    public Word(String country, String capital) {
        Country = country;
        Capital = capital;
    }
    @Id @GeneratedValue
    @Column(name = "word_id")
    private Long id;

    private String Country;
    private String Capital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "test_id")
    private Test test;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordbook_id")
    private WordBook wordBook;

    public void setWordBook(WordBook wordBook, Long key){
        this.wordBook = wordBook;
        wordBook.getMap().put(key,this);
    }

    public static Word createWord(String country, String capital, WordBook wordBook) { //단어장에 단어 추가
        Word word = new Word(country,capital);
        wordBook.setWordKey(wordBook.getWordKey()+1L); //단어 번호 하나 올려주기
        word.setWordBook(wordBook,wordBook.getWordKey());
        return word;
    }


}
