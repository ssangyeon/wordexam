package toy.example.wordexam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Challenger {
    @Id
    @GeneratedValue
    @Column(name = "challenger_id")
    private Long id;

    private String name;
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "challenger", cascade = CascadeType.ALL)
    private List<Distribute> distributeList = new ArrayList<>();  //배포된 시험들

    public void setGroup(Group group){
        this.group = group;
        group.getChallengerList().add(this);
    }

    public static Challenger createChallenger(String name, Group group, String password){
        Challenger challenger = new Challenger();
        challenger.setName(name);
        challenger.setGroup(group);
        challenger.setPassword(password);
        return challenger;
    }

}
