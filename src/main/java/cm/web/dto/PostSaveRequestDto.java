package cm.web.dto;

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
    private User author;

    @Builder
    public PostSaveRequestDto(String title, String content, User author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Post toEntity(){ //  게시물 생성 후 리턴
        return Post.builder()
                .title(title)
                .content(content)
                .user(author)
                .build();
    }
}
