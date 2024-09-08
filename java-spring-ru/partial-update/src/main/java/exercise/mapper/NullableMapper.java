package exercise.mapper;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Optional;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class NullableMapper {

    public <T> Optional<T> wrap(T entity) {
        return Optional.of(entity);
    }

    public <T> T unwrap(Optional<T> nullable) {
        return nullable.orElse(null);
    }

    @Condition
    public <T> boolean isPresent(Optional<T> nullable) {
        return nullable != null;
    }
}
