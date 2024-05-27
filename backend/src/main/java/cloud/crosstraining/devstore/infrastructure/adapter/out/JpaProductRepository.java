package cloud.crosstraining.devstore.infrastructure.adapter.out;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaProductRepository extends PagingAndSortingRepository<ProductEntity, Long> {
}
