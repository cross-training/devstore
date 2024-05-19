package cloud.crosstraining.devstore.infrastructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
}