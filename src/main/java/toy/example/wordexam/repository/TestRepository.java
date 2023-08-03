package toy.example.wordexam.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toy.example.wordexam.domain.Test;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TestRepository {

    private final EntityManager em;

    public void save(Test test){
        em.persist(test);
    }

    public Test findOne(Long id){
        return em.find(Test.class, id);
    }

    public List<Test> findAll(){
        return em.createQuery("select t from Test t", Test.class)
                .getResultList();
    }

    public List<Test> findByName(String name){
        return em.createQuery("select t from Test t where t.name =: name", Test.class)
                .setParameter("name", name)
                .getResultList();
    }
}
