package cloud.crosstraining.devstore.domain.entity;
import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String name;
    private String description;
    private String link;
    private List<String> technologies;
}
