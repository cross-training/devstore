package cloud.crosstraining.devstore.product.repository;

import cloud.crosstraining.devstore.product.entity.Category;
import cloud.crosstraining.devstore.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findByCategory(Category category);
}
