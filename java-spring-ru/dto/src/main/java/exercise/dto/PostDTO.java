package exercise.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import exercise.model.Post;
import lombok.Getter;
import lombok.Setter;

// BEGIN
public record PostDTO(
        @JsonProperty long id,
        @JsonProperty String title,
        @JsonProperty String body,
        @JsonProperty List<CommentDTO> comments) {

    @JsonCreator
    public PostDTO {
        comments = List.copyOf(comments);
    }

    public PostDTO(Post post, List<CommentDTO> comments) {
        this(post.getId(), post.getTitle(), post.getBody(), List.copyOf(comments));
    }
}
// END
