package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

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
    ResponseEntity<List<Post>> readAll() {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }

    @GetMapping("/posts/{id}")
    ResponseEntity<Post> read(@PathVariable String id) {
        var result = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        return ResponseEntity.of(result);
    }

    @PostMapping("/posts")
    ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.created(URI.create("/posts/" + post.getId()))
                .body(post);
    }

    @PutMapping("/posts/{id}")
    ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post ref) {
        var postOpt = posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        if (postOpt.isPresent()) {
            var post = postOpt.get();
            post.setId(ref.getId());
            post.setTitle(ref.getTitle());
            post.setBody(ref.getBody());

            return ResponseEntity.ok(post);
        }

        return ResponseEntity.status(204).build();
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
