package toy.example.wordexam.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toy.example.wordexam.domain.Challenger;
import toy.example.wordexam.domain.Group;
import toy.example.wordexam.domain.Word;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupRepository {

    private final EntityManager em;

    public void save(Group group) {
        em.persist(group);
    }

    public Group findOne(Long id){
        return em.find(Group.class,id);
    }

    public List<Group> findByName(String name){
        return em.createQuery("select g from Group g where g.groupName =: name", Group.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Group> findAll(){
        return em.createQuery("select g from Group g", Group.class)
                .getResultList();
    }
}
