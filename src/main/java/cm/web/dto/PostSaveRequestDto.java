package cm.web.dto;

import cm.config.auth.LoginUser;
import cm.domain.post.Post;
import cm.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;

    @Builder
    public PostSaveRequestDto(String title, String content, Long author){
        this.title = title;
        this.content = content;
    }

    public Post toEntity(User user){ //  작성자(User)받아서 게시물 생성 후 리턴
        return Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
