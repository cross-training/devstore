package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.application.port.out.CategoryRepository;
import cloud.crosstraining.devstore.domain.Category;
import cloud.crosstraining.devstore.domain.Product;
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
    protected Category toDomain(CategoryEntity entity) {
        return  new Category(entity.getId(),entity.getCode(), entity.getName(), entity.getDescription());
    }

    @Override
    protected CategoryEntity toEntity(Category entity) {
        return new CategoryEntity(entity.getId(), entity.getCode(), entity.getName(), entity.getDescription());
    }
}
