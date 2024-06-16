package cloud.crosstraining.devstore.rating.domain;

import cloud.crosstraining.devstore.common.domain.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(callSuper = false)
public class Rating implements Entity<Long> {
    private Long id;
    private Long productId;
    @Email
    private String email;
    @Min(value=0, message = "rating can't minor that 0")
    @Max(value=5, message = "rating can't major that 5")
    private Double rating;
    private OffsetDateTime createAt;
}
