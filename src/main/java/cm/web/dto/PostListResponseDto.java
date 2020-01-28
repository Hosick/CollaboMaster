package cm.web.dto;

import cm.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String createdDate;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getUser().getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        this.createdDate = entity.getCreatedDate().format(formatter);
    }

}
