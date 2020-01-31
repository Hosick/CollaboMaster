package cm.service;

import cm.config.auth.LoginUser;
import cm.domain.user.User;
import cm.domain.post.Post;
import cm.domain.post.PostRepository;
import cm.web.dto.PostListResponseDto;
import cm.web.dto.PostResponseDto;
import cm.web.dto.PostSaveRequestDto;
import cm.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional  //  유저 객체와 saveRequestDto를 받아서 toEntity로 게시글 저장
    public Long save(PostSaveRequestDto requestDto, User user) {
        return postRepository.save(requestDto.toEntity(user)).getId();
    }

    @Transactional  //  게시글 수정
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        post.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostResponseDto findById(Long id) {  //  id로 게시글 찾기
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new PostResponseDto(entity);
    }

    @Transactional(readOnly = true) //  전체 게시글 조회
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        postRepository.delete(post);
    }

    /*글 수정페이지에서 로그인한 사용자가 해당 글 작성자인지 확인*/
    public boolean isWriter(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + postId));
        if (post.getUser().getId().equals(userId))
            return true;
        return false;
    }
}
