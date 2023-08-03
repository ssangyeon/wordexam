package toy.example.wordexam.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toy.example.wordexam.domain.Word;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WordRepository {

    private final EntityManager em;

    public void save(Word word){
        if (word.getId() == null) {
            em.persist(word);
        }else{
            em.merge(word);
        }
    }

    public Word findOne(Long id){
        return em.find(Word.class, id);
    }

    public List<Word> findAll(){
        return em.createQuery("select w from Word w", Word.class)
                .getResultList();
    }

}
