package cloud.crosstraining.devstore.catalog.infrastructure.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
