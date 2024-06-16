package cloud.crosstraining.devstore.rating.infrastructure.adapter.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "Rating")
public class RatingEntity {
    @Id
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    private String email;
    private Double rating;
    @Column(name = "create_at")
    private OffsetDateTime createAt;
}