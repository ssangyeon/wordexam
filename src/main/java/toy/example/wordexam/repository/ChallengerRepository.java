package toy.example.wordexam.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import toy.example.wordexam.domain.Challenger;

import java.util.List;

@Repository
public class ChallengerRepository {

    @PersistenceContext //entitymanager은 원래 PersistenceContext 써야하는데 스프링부트가 @autowired도 injection되게 해줌
    private EntityManager em;  // @RequiredArgsConstructor,private final EntityManager em;로 대체가능

    public void save(Challenger challenger){
        em.persist(challenger);
    }

    public Challenger findOne(Long id){
        return em.find(Challenger.class, id);
    }

    public List<Challenger> findAll(){
        return em.createQuery("select c from Challenger c", Challenger.class)
                .getResultList();
    }

    public List<Challenger> findByName(String name){
        return em.createQuery("select c from Challenger c where c.name =: name", Challenger.class)
                .setParameter("name", name)
                .getResultList();
    }

//    public Optional<Challenger> findByGroupName(String groupName) {
//        List<Challenger> challengers = em.createQuery("SELECT c FROM Challenger c WHERE c.group = :groupName", Challenger.class)
//                .setParameter("groupName", groupName)
//                .getResultList();
//
//        if (challengers.isEmpty()) {
//            return Optional.empty();
//        } else {
//            return Optional.of(challengers.get(0));
//        }
//    }

}
