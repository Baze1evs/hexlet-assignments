package exercise.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import exercise.model.Comment;
import lombok.Getter;
import lombok.Setter;

// BEGIN
public record CommentDTO(@JsonProperty long id, @JsonProperty String body) {

    @JsonCreator
    public CommentDTO {}

    public CommentDTO(Comment comment) {
        this(comment.getId(), comment.getBody());
    }
}
// END
