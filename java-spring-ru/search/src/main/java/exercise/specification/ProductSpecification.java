package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withRatingGt(params.getRatingGt()))
                .and(withTitleCont(params.getTitleCont()));
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) ->
                categoryId != null
                        ? criteriaBuilder.equal(root.get("category").get("id"), categoryId)
                        : criteriaBuilder.conjunction();
    }

    private Specification<Product> withPriceGt(Integer price) {
        return (root, query, criteriaBuilder) ->
                price != null
                        ? criteriaBuilder.greaterThan(root.get("price"), price)
                        : criteriaBuilder.conjunction();
    }

    private Specification<Product> withPriceLt(Integer price) {
        return (root, query, criteriaBuilder) ->
                price != null
                        ? criteriaBuilder.lessThan(root.get("price"), price)
                        : criteriaBuilder.conjunction();
    }

    private Specification<Product> withRatingGt(Double rating) {
        return (root, query, criteriaBuilder) ->
                rating != null
                        ? criteriaBuilder.greaterThan(root.get("rating"), rating)
                        : criteriaBuilder.conjunction();
    }

    private Specification<Product> withTitleCont(String titleCont) {
        return (root, query, criteriaBuilder) ->
                titleCont != null
                        ? criteriaBuilder.equal(criteriaBuilder.lower(root.get("title")), titleCont.toLowerCase())
                        : criteriaBuilder.conjunction();
    }
}
// END
