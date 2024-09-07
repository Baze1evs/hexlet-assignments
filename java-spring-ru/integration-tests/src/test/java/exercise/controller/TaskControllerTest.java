package exercise.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    private Task generateTask() {
        Task task = new Task();
        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().paragraph());

        return task;
    }

    @BeforeEach
    public void setUp() {
        Task task = generateTask();
        taskRepository.save(task);
    }

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testShowNegative() throws Exception {
        mockMvc.perform(get("/tasks/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreate() throws Exception {
        Task task = generateTask();

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(task));

        var result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertThatJson(response).isObject()
                .containsEntry("title", task.getTitle())
                .containsEntry("description", task.getDescription());
    }

    @Test
    public void testUpdate() throws Exception {
        Task task = taskRepository.findAll().getFirst();

        var requestBody = Map.of("title", "test-title", "description", "test-description");

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(requestBody));

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        var response = result.getResponse().getContentAsString();

        assertThatJson(response).isObject()
                .containsAllEntriesOf(requestBody);

        Task updatedTask = taskRepository.findById(task.getId())
                        .orElseThrow();

        assertThat(updatedTask.getId()).isEqualTo(task.getId());
        assertThat(updatedTask.getTitle()).isEqualTo(requestBody.get("title"));
        assertThat(updatedTask.getDescription()).isEqualTo(requestBody.get("description"));
        assertThat(updatedTask.getCreatedAt()).isEqualTo(task.getCreatedAt());
    }

    @Test
    public void testUpdateNegative() throws Exception {
        var requestBody = Map.of("title", "test-title", "description", "test-description");

        var request = put("/tasks/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(requestBody));

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDelete() throws Exception {
        Task task = taskRepository.findAll().getFirst();

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        assertThat(taskRepository.existsById(task.getId())).isFalse();
    }
    // END
}
