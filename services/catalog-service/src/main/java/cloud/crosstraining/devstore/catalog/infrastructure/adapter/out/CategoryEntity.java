package cloud.crosstraining.devstore.catalog.infrastructure.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "tbl_categories")
public class CategoryEntity {

    @Id
    private String id;
    private String name;
    private String description;
}
