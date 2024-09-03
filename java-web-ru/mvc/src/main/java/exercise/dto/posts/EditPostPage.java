package exercise.dto.posts;

import java.util.List;
import java.util.Map;

import exercise.model.Post;
import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// BEGIN
@Getter
public class EditPostPage extends BuildPostPage {
    private final Long id;

    public EditPostPage(Long id, String name, String body, Map<String, List<ValidationError<Object>>> errors) {
        super(name, body, errors);
        this.id = id;
    }
}
// END
