package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    List<Post> readAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        int start = limit * page;
        int end = start + limit;

        return posts.subList(start, end);
    }

    @GetMapping("/posts/{id}")
    Optional<Post> read(@PathVariable String id) {
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
    }

    @PostMapping("/posts")
    Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    Post update(@PathVariable String id, @RequestBody Post ref) {
        var postOpt = read(id);

        if (postOpt.isPresent()) {
            var post = postOpt.get();
            post.setId(ref.getId());
            post.setTitle(ref.getTitle());
            post.setBody(ref.getBody());
            return post;
        }

        return null;
    }

    @DeleteMapping("/posts/{id}")
    void delete(@PathVariable String id) {
        posts.removeIf(post -> post.getId().equals(id));
    }
    // END
}
