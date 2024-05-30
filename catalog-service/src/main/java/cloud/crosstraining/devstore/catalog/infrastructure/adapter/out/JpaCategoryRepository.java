package cloud.crosstraining.devstore.catalog.infrastructure.adapter.out;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepository extends PagingAndSortingRepository<CategoryEntity,String> {
}
