package cloud.crosstraining.devstore.domain;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements  Entity<Long>  {
    private Long id;
    private String code;
    private String name;
    private Double price;
    private String description;
    private List<String> categories;
    private String url;
}
