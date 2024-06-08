package cloud.crosstraining.devstore.catalog.domain;
import cloud.crosstraining.devstore.common.domain.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Category implements Entity<String>, Serializable {
    private String id;
    private String name;
    private String description;
}
