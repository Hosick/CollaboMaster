package cm.controller;

import cm.domain.Post;
import cm.domain.User;
import cm.repository.PostRepository;
import cm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;


    /*메인페이지*/
    @RequestMapping
    public String main(Model model){
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        return "main";
    }

    /*로그인 후 메인페이지*/
    @RequestMapping("login")
    public String login(Model model, @RequestParam("id") String id){
        User user = userRepository.findById(id).get();
        List<Post> posts = postRepository.findAll();
        model.addAttribute(user);
        model.addAttribute("posts",posts);
        return "main";
    }

}
