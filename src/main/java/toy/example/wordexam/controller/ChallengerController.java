package toy.example.wordexam.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toy.example.wordexam.domain.Challenger;
import toy.example.wordexam.dto.ChallengerForm;
import toy.example.wordexam.service.ChallengerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChallengerController {
    private final ChallengerService challengerService;

    @GetMapping(value = "/challengers/new")
    public String createForm(Model model) {
        model.addAttribute("challengerForm", new ChallengerForm());
        return "challengers/createChallengerForm";
    }

    @PostMapping("challengers/new")
    public String createChallenger(@Valid ChallengerForm form, BindingResult result){
        if(result.hasErrors()){
            return "challengers/createChallengerForm";
        }
        challengerService.join(form.getChallengerName(),form.getGroupName(),form.getChallengerPassword());

        return "redirect:/";
    }
    @PostMapping("/challengers/login")
    public String login(@Valid ChallengerForm form, BindingResult result, HttpSession session){
        ChallengerForm loginResult = challengerService.login(form);
        if(loginResult != null){
            //login 성공
            session.setAttribute("loginChallengerName",loginResult.getChallengerName());
            return "challengers/main";
        }else {
            //login 실패
            return "challengers/login";
        }
    }

    @GetMapping("/challengers/login")
    public String loginForm(){
        return "challengers/login";
    }

    @GetMapping("/challengerList")
    public String challengerLists(Model model) {
        List<Challenger> challengers = challengerService.findChallengers();
        //어떠한 html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("challengers", challengers);
        return "challengers/challengerList";
    }
}
