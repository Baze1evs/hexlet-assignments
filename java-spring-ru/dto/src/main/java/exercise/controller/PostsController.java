package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping(path = "/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<PostDTO> readAll() {
        return postRepository.findAll().stream()
                .map(post -> {
                    var comments = commentRepository.findByPostId(post.getId()).stream()
                            .map(CommentDTO::new)
                            .toList();

                    return new PostDTO(post, comments);
                }).toList();
    }

    @GetMapping(path = "/{id}")
    public PostDTO read(@PathVariable long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        var comments = commentRepository.findByPostId(post.getId()).stream()
                .map(CommentDTO::new)
                .toList();

        return new PostDTO(post, comments);
    }
}
// END
