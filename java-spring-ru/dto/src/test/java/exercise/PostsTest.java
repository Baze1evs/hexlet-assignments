package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import exercise.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PostsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testIndex() throws Exception {

        var result = mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray().hasSize(6);
    }

    @Test
    public void testShow() throws Exception {
        var testCommentDTO = new CommentDTO(6L, "Comment Body 6");

        var comments = List.of(testCommentDTO);

        var testPostDTO = new PostDTO(3L, "Post Title 3", "Post Body 3", comments);

        var result = mockMvc.perform(get("/posts/{id}", 3L))
            .andExpect(status().isOk())
            .andReturn();

        var body = result.getResponse().getContentAsString();

        PostDTO post = om.readValue(body, new TypeReference<PostDTO>() { });
        assertThat(post).usingRecursiveComparison().isEqualTo(testPostDTO);
    }

    @Test
    public void testShowNegative() throws Exception {
        var result = mockMvc.perform(get("/posts/{id}", 100))
                .andExpect(status().isNotFound())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Post with id 100 not found");
    }

}
