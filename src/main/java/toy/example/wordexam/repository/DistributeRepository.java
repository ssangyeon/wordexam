package toy.example.wordexam.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toy.example.wordexam.domain.Distribute;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DistributeRepository {

    private final EntityManager em;

    public void save(Distribute distribute) {
        em.persist(distribute);
    }

    public List<Distribute> findAll(){
        return em.createQuery("select d from Distribute d",Distribute.class)
                .getResultList();
    }
    public Distribute findOne(Long id) {
        return em.find(Distribute.class, id);
    }

}
