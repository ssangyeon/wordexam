package toy.example.wordexam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Distribute {
    @Id @GeneratedValue
    @Column(name = "distribute_id")
    private Long id;

    @Column(name = "distribute_time")
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenger_id")
    private Challenger challenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    public void setChallenger(Challenger challenger){
        this.challenger = challenger;
        challenger.getDistributeList().add(this);
    }

    public void setTest(Test test){
        this.test = test;
        test.getDistributeList().add(this);
        test.setTeststatus((TestStatus.YET));
    }

    public static Distribute createDistribute(Test test, Challenger challenger){
        Distribute distribute = new Distribute();
        distribute.setTest(test); test.setTeststatus(TestStatus.DISTRIBUTE);
        distribute.setChallenger(challenger);
        distribute.setTime(LocalDateTime.now());
        return distribute;
    }
}
