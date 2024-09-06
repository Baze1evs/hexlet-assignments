package exercise.controller;

import exercise.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping(path = "/comments")
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> readAll() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Comment read(@PathVariable long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        commentRepository.save(comment);

        return comment;
    }

    @PutMapping(path = "/{id}")
    public Comment update(@PathVariable long id, @RequestBody Comment c) {
        Comment comment = read(id);

        comment.setBody(c.getBody());

        return comment;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        commentRepository.deleteById(id);
    }
}
// END
