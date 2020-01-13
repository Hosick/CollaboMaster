package cm.web;

import cm.service.PostService;
import cm.web.dto.PostResponseDto;
import cm.web.dto.PostSaveRequestDto;
import cm.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    @PostMapping("/api/v1/post")    //  게시글 저장
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
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
    public Long delete(@PathVariable Long id){
        postService.delete(id);
        return id;
    }
}