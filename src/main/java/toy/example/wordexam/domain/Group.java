package toy.example.wordexam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "Class")
public class Group {
    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    private String groupName;

    @OneToMany(mappedBy = "group")
    private List<Challenger> challengerList = new ArrayList<>();

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
