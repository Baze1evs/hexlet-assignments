package exercise;

import exercise.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Bean;
import net.datafaker.Faker;

@EnableJpaAuditing
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Faker getFaker() {
        return new Faker();
    }

    @Bean
    public static User getDefaultUser() {
        var user = new User();
        user.setName("Bazelevs");
        user.setEmail("test@email.com");
        return user;
    }
}
