package cloud.crosstraining.devstore.rating.domain;

import cloud.crosstraining.devstore.common.domain.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Rating implements Entity<Long> {
    private Long id;
    private Long productId;
    private String email;
    private Double rating;
    private OffsetDateTime datetime;
}
