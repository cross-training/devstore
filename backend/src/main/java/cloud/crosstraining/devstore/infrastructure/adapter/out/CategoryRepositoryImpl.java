package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.application.port.out.CategoryRepository;
import cloud.crosstraining.devstore.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl extends RepositoryImpl<Category,Long,CategoryEntity>  implements CategoryRepository {

    private final JpaCategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryImpl(JpaCategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected Category toDomain(CategoryEntity categoryEntity) {
        return null;
    }

    @Override
    protected CategoryEntity toEntity(Category entity) {
        return null;
    }
}
