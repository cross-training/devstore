package cloud.crosstraining.devstore.domain.entity;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String name;
    private List<Technology> technologies;
}
