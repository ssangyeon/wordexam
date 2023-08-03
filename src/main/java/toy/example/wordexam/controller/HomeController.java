package toy.example.wordexam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
    //기본페이지 요청 메서드
    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";//=>templates 폴더의 home.html을 찾아감
    }

}
