package toy.example.wordexam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Getter @Setter
public class WordBook {
    @Id @GeneratedValue
    @Column(name = "wordbook_id")
    private Long id;

    private String name;

    private Long wordKey;

    @OneToMany(mappedBy = "wordBook")
    private Map<Long,Word> map = new LinkedHashMap<>(); //나중에 조금 수정  단어들 리스트

    public static WordBook createWordBook(String name) {
        WordBook wordBook = new WordBook();
        wordBook.setName(name); wordBook.setWordKey(0L);
        return wordBook;
    }
}
