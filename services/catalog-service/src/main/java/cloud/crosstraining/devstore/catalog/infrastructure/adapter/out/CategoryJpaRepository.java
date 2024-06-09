package cloud.crosstraining.devstore.catalog.infrastructure.adapter.out;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity,String> {
}
