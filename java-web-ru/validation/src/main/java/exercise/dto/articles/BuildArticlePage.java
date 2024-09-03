package exercise.dto.articles;

import io.javalin.validation.ValidationError;
import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

// BEGIN
public record BuildArticlePage(String title, String content, Map<String, List<ValidationError<Object>>> errors) {
}
// END
