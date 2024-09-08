package exercise.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
public class ProductUpdateDTO {
    private String title;
    private int price;
}
// END
