package cm.domain.post;

import cm.domain.user.Role;
import cm.domain.user.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @After
    public void cleanup(){  //  테스트를 마친 후 게시물 전부 삭제
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 제목";
        String content = "테스트 본문";
        User testUser = User.builder()
                .name("홍길동")
                .email("email")
                .picture("picture")
                .role(Role.USER)
                .build();

        postRepository.save(Post.builder()
                .title(title)
                .content(content)
                /*.user(testUser)*/
                .build());

        //when
        List<Post> posts = postRepository.findAll();

        //then
        Post postFirst = posts.get(0);
        assertThat(postFirst.getTitle()).isEqualTo(title);
        assertThat(postFirst.getContent()).isEqualTo(content);
    }



}