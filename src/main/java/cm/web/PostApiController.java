package cm.web;

import cm.config.auth.LoginUser;
import cm.config.auth.dto.SessionUser;
import cm.domain.user.User;
import cm.service.PostService;
import cm.service.UserService;
import cm.web.dto.PostResponseDto;
import cm.web.dto.PostSaveRequestDto;
import cm.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;
    private final UserService userService;

    @PostMapping("/api/v1/post")    //  게시글 저장 POST (세션에 있는 유저 id로 User 객체를 찾아서 requestDto와 같이 전송.
    public Long save(@RequestBody PostSaveRequestDto requestDto, @LoginUser SessionUser user) {
        return postService.save(requestDto, userService.findById(user.getId()));
    }

    @GetMapping("/api/v1/post/{id}")    //  게시글 수정 GET
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/api/v1/post/{id}")    //  게시글 수정 PUT
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/post/{id}") //  게시글 삭제 DELETE
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}