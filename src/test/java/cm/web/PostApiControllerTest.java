package cm.web;

import cm.domain.post.Post;
import cm.domain.post.PostRepository;
import cm.domain.user.Role;
import cm.domain.user.User;
import cm.domain.user.UserRepository;
import cm.service.PostService;
import cm.web.dto.PostSaveRequestDto;
import cm.web.dto.PostUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        //given
        String title = "testTitle";
        String content = "testContent";
        User user = User.builder()
                .name("testAuthor")
                .email("test")
                .picture("test")
                .role(Role.USER)
                .build();
        userRepository.save(user);

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        String url = "http://localhost:" + port + "/api/v1/post";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getUser().getName()).isEqualTo(user.getName());
    }

    @Test
    public void Post_수정된다() throws Exception {
        //given
        User user = User.builder()
                .name("testAuthor")
                .email("test")
                .picture("test")
                .role(Role.USER)
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .title("title")
                .content("content")
                .user(user)
                .build();
        postRepository.save(post);


        String title = "changedTitle";
        String content = "changedContent";
        Long updateId = post.getId();

        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .title(title)
                .content(content)
                .build();

        String url = "http://localhost:" + port + "/api/v1/post/" + updateId;

        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);


        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> all = postRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

}