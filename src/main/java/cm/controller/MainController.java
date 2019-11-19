package cm.controller;

import cm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("main")
public class MainController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("main2")
    public String main3(){
        return "main/main2";
    }

}
