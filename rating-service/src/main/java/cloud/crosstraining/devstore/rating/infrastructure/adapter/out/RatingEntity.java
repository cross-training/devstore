package cloud.crosstraining.devstore.rating.infrastructure.adapter.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Rating")
public class RatingEntity {
    @Id
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    private String email;
    private Double rating;
    @Column(name = "event_datetime")
    private OffsetDateTime datetime;
}