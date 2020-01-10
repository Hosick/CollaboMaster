package cm.domain.post;

import cm.domain.user.Role;
import cm.domain.user.User;
import cm.domain.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {  //  테스트를 마친 후 게시물, 유저 전부 삭제
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 제목";
        String content = "테스트 본문";
        User testUser = User.builder()
                .name("홍길동")
                .email("email")
                .picture("picture")
                .role(Role.USER)
                .build();
        userRepository.save(testUser);

        postRepository.save(Post.builder()
                .title(title)
                .content(content)
                .user(testUser)
                .build());

        //when
        List<Post> posts = postRepository.findAll();

        //then
        Post postFirst = posts.get(0);
        assertThat(postFirst.getTitle()).isEqualTo(title);
        assertThat(postFirst.getContent()).isEqualTo(content);
        assertThat(postFirst.getUser().getName()).isEqualTo(testUser.getName());
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2020,1,10,0,0,0);
        postRepository.save(Post.builder()
        .title("title")
        .content("content")
        .build());

        //when
        List<Post> posts = postRepository.findAll();

        //then
        Post post = posts.get(0);

        System.out.println(">>>>>>>>>> createDate = "+post.getCreatedDate()+", modifiedDate = "+post.getModifiedDate());

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}