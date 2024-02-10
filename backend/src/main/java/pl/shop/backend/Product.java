package pl.shop.backend;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product {

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class Measure {
        private String unit;
        private int value;
    }

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public static class RecommendedDailyDose {
        private int energy;
        private double fat;
        private double saturatedFattyAcids;
        private double carbs;
        private double sugar;
        private double proteins;
        private double salt;
    }

    @Id
    private ObjectId id;
    private Integer availableCount;
    private String category;
    private String description;
    private String ingredients;
    private Measure measure;
    private String name;
    private List<String> otherInformation;
    private Double price;
    private Double discountPrice;
    private String producer;
    private RecommendedDailyDose recommendedDailyDose;
    private String storingConditions;
    private String usageInstruction;

}
