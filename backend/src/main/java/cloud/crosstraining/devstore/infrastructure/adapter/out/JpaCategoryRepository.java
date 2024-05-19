package cloud.crosstraining.devstore.infrastructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
