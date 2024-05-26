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
public class Category extends EntityBase implements Entity<String> {
    private String id;
    private String name;
    private String description;
}
