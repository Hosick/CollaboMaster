package cm.controller;

import cm.domain.Post;
import cm.domain.User;
import cm.model.UserRegistrationModel;
import cm.repository.PostRepository;
import cm.repository.UserRepository;
import cm.service.PostService;
import cm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;


    /*메인페이지*/
    @GetMapping
    public String main(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "main";
    }

    /*로그인 후 메인페이지*/
    @RequestMapping("login")
    public String login(Model model, @RequestParam("id") String id) {
        User user = userService.findById(id);
        List<Post> posts = postService.findAll();
        model.addAttribute(user);
        model.addAttribute("posts", posts);
        return "main";
    }

    /*로그 아웃 후 메인페이지*/
    @PostMapping
    public String logout(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "main";
    }

    /*글쓰기 페이지*/
    @GetMapping("write")
    public String write(Model model) {
        return "writePost";
    }

    /*회원가입 페이지*/
    @GetMapping("signUp")
    public String signUp(Model model, UserRegistrationModel userModel) { return "signUp/signUp"; }

    @PostMapping("signUp")
    public String signUp(@Valid UserRegistrationModel userModel, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            return "signUp/signUp";
        userService.save(userModel);
        return "redirect:";
    }
}
