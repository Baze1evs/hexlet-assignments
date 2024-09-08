package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

// BEGIN
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

    @Mapping(source = "title", target = "name")
    @Mapping(source = "price", target = "cost")
    @Mapping(source = "vendorCode", target = "barcode")
    Product toProduct(ProductDTO dto);

    @Mapping(source = "title", target = "name")
    @Mapping(source = "price", target = "cost")
    @Mapping(source = "vendorCode", target = "barcode")
    Product toProduct(ProductCreateDTO dto);

    @Mapping(source = "price", target = "cost")
    Product toProduct(ProductUpdateDTO dto);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode", source = "barcode")
    ProductDTO toProductDTO(Product model);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode", source = "barcode")
    ProductCreateDTO toProductCreateDTO(Product model);

    @Mapping(target = "price", source = "cost")
    ProductUpdateDTO toProductUpdateDTO(Product model);

    @Mapping(source = "price", target = "cost")
    void update(ProductUpdateDTO dto, @MappingTarget Product model);
}
// END
