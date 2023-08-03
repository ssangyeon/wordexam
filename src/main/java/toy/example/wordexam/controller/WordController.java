package toy.example.wordexam.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toy.example.wordexam.domain.Challenger;
import toy.example.wordexam.domain.Group;
import toy.example.wordexam.domain.Word;
import toy.example.wordexam.dto.QuestionForm;
import toy.example.wordexam.dto.WordForm;
import toy.example.wordexam.service.TestService;
import toy.example.wordexam.service.WordService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;
    private final TestService testService;

    @GetMapping("/words/new")
    public String createForm(org.springframework.ui.Model model) {
        model.addAttribute("form", new WordForm());
        return "words/createWordForm";
    }

    @PostMapping("/words/new")
    public String createWord(@Valid WordForm form, BindingResult result){
        if(result.hasErrors()){
            return "words/createWordForm";
        }
        Long wordBookId = wordService.isValidWordBook(form.getWordBook());
        wordService.saveWord(form.getCountry(),form.getCapital(),wordBookId);
        return "redirect:/";
    }

    @GetMapping("/wordlist")
    public String wordList(org.springframework.ui.Model model){
        List<Word> wordList = wordService.findWords();
        model.addAttribute("words",wordList);
        return "words/wordList";
    }

    @GetMapping("/words/question")
    public String questionForm(Model model){
        model.addAttribute("QForm",new QuestionForm());
        return "words/questionForm";
    }

    @PostMapping("/words/question")
    public String question(@Valid QuestionForm form, BindingResult result){
        if(result.hasErrors()){
            return "words/questionForm";
        }
        System.out.println(form.toString());
        Long validWordBook = wordService.isValidWordBook(form.getWordBook()); //유효한 단어장인지
        Long testId = testService.test(validWordBook,form.getTestName(),form.getStart_range(),form.getEnd_range(),form.getCutLine(),form.getTestTime()); //테스트 생성(단어장,범위,컽,시험시간)
        Group group = testService.findGroup(form.getGroup());
        List<Challenger> challengerList = group.getChallengerList(); //그룹별로 배포하기
        testService.distribute(testId,challengerList);
        return "redirect:/";
    }
}
