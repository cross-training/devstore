package cloud.crosstraining.devstore.catalog.domain;
import cloud.crosstraining.devstore.common.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Product implements Entity<Long>, Serializable {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private List<String> categories;
    private String url;
    private Double reviews;
    private String imageUrl;
}
