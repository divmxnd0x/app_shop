package pl.shop.backend;

import org.bson.types.ObjectId;
import pl.shop.backend.Product.Measure;

import java.util.Optional;

public record ProductListItemDTO(ObjectId id,
                                 String name,
                                 Double price,
                                 Double discountPrice,
                                 Measure measure,
                                 Double pricePerMeasure) {
    public static ProductListItemDTO toDto(Product product) {
        Double pricePerMeasure = Optional.of(product.getMeasure())
                .map(measure -> (1000.0 / measure.getValue()) * product.getPrice())
                .orElse(product.getPrice());

        return new ProductListItemDTO(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscountPrice(),
                product.getMeasure(),
                Math.round(pricePerMeasure * 100.0) / 100.0);
    }
}
