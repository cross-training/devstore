package cloud.crosstraining.devstore.catalog.domain;
import cloud.crosstraining.devstore.common.domain.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
public class Category implements Entity<String>, Serializable {
    @NotNull(message = "id can't null")
    @NotEmpty(message = "id can't empty")
    @Size(max = 30, min = 6 , message = "id can't minor that 6 and mayor than 30")
    private String id;
    @NotBlank(message = "name can't empty")
    @NotNull(message = "name can't null")
    @Size( max = 80, message = "name exceeds 80 characters")
    private String name;
    private String description;
}
