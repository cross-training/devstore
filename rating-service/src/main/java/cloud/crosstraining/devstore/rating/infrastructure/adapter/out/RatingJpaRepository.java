package cloud.crosstraining.devstore.rating.infrastructure.adapter.out;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingJpaRepository extends PagingAndSortingRepository<RatingEntity,Long> {
}
