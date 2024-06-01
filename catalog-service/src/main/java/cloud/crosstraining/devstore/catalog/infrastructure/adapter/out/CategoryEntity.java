package cloud.crosstraining.devstore.catalog.infrastructure.adapter.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categories")
public class CategoryEntity {

    @Id
    private String id;
    private String name;
    private String description;
}
