package toy.example.wordexam.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import toy.example.wordexam.domain.Challenger;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor//모든필드를 매개변수로하는 생성자
@ToString
public class ChallengerForm {
    @NotEmpty(message = "이름은 필수 입니다")
    private String challengerName;
    @NotEmpty(message = "반은 필수 입니다")
    private String groupName;
    @NotEmpty(message = "비밀번호는 필수 입니다")
    private String challengerPassword;

    public static ChallengerForm toChallengerForm(Challenger challenger){
        ChallengerForm challengerForm = new ChallengerForm();
        challengerForm.setChallengerName(challengerForm.getChallengerName());
        challengerForm.setChallengerPassword(challengerForm.getChallengerPassword());
        challengerForm.setGroupName(challengerForm.getGroupName());
        return challengerForm;
    }

}


