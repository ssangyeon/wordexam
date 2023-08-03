package toy.example.wordexam.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import toy.example.wordexam.domain.WordBook;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WordBookRepository {

    private final EntityManager em;

    @Transactional
    public void save(WordBook wordBook) {
        em.persist(wordBook);
    }
    public WordBook findOne(Long id) {
        return em.find(WordBook.class, id);
    }

    public List<WordBook> findByName(String name){
        return em.createQuery("select b from WordBook b where b.name=:name",WordBook.class)
                .setParameter("name",name)
                .getResultList();
    }

}
