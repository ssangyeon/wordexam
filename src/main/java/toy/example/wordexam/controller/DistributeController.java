package toy.example.wordexam.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toy.example.wordexam.domain.Challenger;
import toy.example.wordexam.domain.Distribute;
import toy.example.wordexam.domain.Group;
import toy.example.wordexam.dto.DistributeForm;
import toy.example.wordexam.service.TestService;
import toy.example.wordexam.service.WordService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DistributeController {

    private final TestService testService;
    private final WordService wordService;

    @GetMapping("/distribute/new")
    public String createForm(Model model){
        model.addAttribute("distributeForm", new DistributeForm());
        return "distribute/createDistribute";
    }

    @PostMapping("/distribute/new")
    public String createDistribute(@Valid DistributeForm form, BindingResult result){
        if(result.hasErrors()){
            return "distribute/createDistributeForm";
        }
        String testName = form.getTestName();
        String groupName = form.getGroupName();
        Group group = testService.findGroup(groupName);
        List<Challenger> challengerList = group.getChallengerList();
        Long testId = testService.findTestId(testName);
        testService.distribute(testId,challengerList);

        return "redirect:/";
    }

    @GetMapping("/distributions")
    public String distributions(Model model){
        List<Distribute> distributeList = testService.findDistributeList();
        model.addAttribute("distributions",distributeList);
        return "distribute/distributionList";
    }
}
