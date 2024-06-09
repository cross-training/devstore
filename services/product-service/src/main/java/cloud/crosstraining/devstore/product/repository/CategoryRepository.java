package cloud.crosstraining.devstore.product.repository;

import cloud.crosstraining.devstore.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
