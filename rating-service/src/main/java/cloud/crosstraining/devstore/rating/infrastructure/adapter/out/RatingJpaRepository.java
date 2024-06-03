package cloud.crosstraining.devstore.rating.infrastructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingJpaRepository extends JpaRepository<RatingEntity,Long> {
}
