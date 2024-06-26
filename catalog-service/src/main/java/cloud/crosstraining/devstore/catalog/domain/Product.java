package cloud.crosstraining.devstore.catalog.domain;
import cloud.crosstraining.devstore.common.domain.Entity;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(callSuper=false)
public class Product implements Entity<Long>, Serializable {
    private Long id;
    @NotBlank(message = "name can't empty")
    @NotNull(message = "name can't null")
    @Size( max = 80, message = "name exceeds 80 characters")
    private String name;
    private String description;
    private Category category;
    @Positive(message= "price can't negative or zero")
    private Double price;
    @Positive(message= "price can't negative or zero")
    private Double stock;
    @Size( max = 255, message = "name exceeds 255 characters")
    private String url;
    @Positive(message= "reviews can't negative or zero")
    private Long reviews;
    @Min(value=0, message = "rating can't minor that 0")
    @Max(value=5, message = "rating can't major that 5")
    private Double rating;
    @Size( max = 255, message = "imageUrl exceeds 255 characters")
    private String imageUrl;
    private Date createAt;
}
