package exercise.dto.posts;

import java.util.List;
import java.util.Map;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildPostPage {
    protected String name;
    protected String body;
    protected Map<String, List<ValidationError<Object>>> errors;
}
