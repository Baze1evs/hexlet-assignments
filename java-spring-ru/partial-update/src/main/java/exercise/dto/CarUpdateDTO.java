package exercise.dto;

import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

// BEGIN
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Getter
@Setter
public class CarUpdateDTO {
    private Optional<String> manufacturer;
    private Optional<String> model;
    private Optional<Integer> enginePower;
}
// END
