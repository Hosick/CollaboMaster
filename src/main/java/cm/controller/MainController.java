package cm.controller;

import cm.domain.User;
import cm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    UserRepository userRepository;

    /*메인페이지*/
    @RequestMapping
    public String main(){
        return "main";
    }

    /*로그인 후 메인페이지*/
    @RequestMapping("login")
    public String login(Model model, @RequestParam("id") String id){
        User user = new User();
        user = userRepository.findById(id);
        model.addAttribute(user);
        return "main";
    }

}
