package toy.example.wordexam.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WordForm {
    @NotEmpty
    private String Country;
    @NotEmpty
    private String Capital;
    @NotEmpty
    private String wordBook;


}
