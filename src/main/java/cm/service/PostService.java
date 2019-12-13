package cm.service;

import cm.domain.Post;
import cm.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }
}
