package toy.example.wordexam.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class QuestionForm {
    @NotEmpty
    private String wordBook;
    @NotEmpty
    private String group; //반 단위로 내기
    private String testName;
    private Long start_range; //단어 범위
    private Long end_range;
    private Long cutLine;
    private Long testTime;

}
