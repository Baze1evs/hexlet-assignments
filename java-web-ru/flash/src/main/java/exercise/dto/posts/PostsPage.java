package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import exercise.dto.BasePage;

// BEGIN
@Getter
public class PostsPage extends BasePage {
    private final List<Post> posts;

    public PostsPage(List<Post> posts, String flash) {
        super(flash);
        this.posts = posts;
    }

    public PostsPage(List<Post> posts) {
        this(posts, null);
    }
}
// END
