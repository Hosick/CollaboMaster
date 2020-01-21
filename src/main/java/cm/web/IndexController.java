package cm.web;

import cm.config.auth.LoginUser;
import cm.config.auth.dto.SessionUser;
import cm.domain.user.User;
import cm.service.PostService;
import cm.web.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping("/")    //  메인 페이지 호출
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/post/save")   //  새 글 작성 페이지 호출
    public String postSave() {
        return "post-save";
    }

    @GetMapping("/post/update/{id}")    //  글 수정 페이지 호출
    public String postUpdate(@PathVariable Long id, Model model) {
        PostResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);
        return "post-update";
    }
}