package cloud.crosstraining.devstore.domain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Product extends EntityBase implements Entity<Long>  {
    private Long id;
    private String code;
    private String name;
    private Double price;
    private String description;
    private List<String> categories;
    private String url;
    private Double reviews;
    private String imageUrl;
}
