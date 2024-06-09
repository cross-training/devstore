package cloud.crosstraining.devstore.catalog.infrastructure.adapter.out;

import cloud.crosstraining.devstore.catalog.application.port.out.CategoryRepository;
import cloud.crosstraining.devstore.catalog.domain.Category;
import cloud.crosstraining.devstore.common.infrastructure.adapter.out.RepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryRepositoryImpl extends RepositoryImpl<Category,String,CategoryEntity> implements CategoryRepository {

    @Override
    protected Class<CategoryEntity> classEntity() {
        return CategoryEntity.class;
    }

    @Autowired
    public CategoryRepositoryImpl(CategoryJpaRepository jpaRepository, EntityManagerFactory entityManagerFactory) {
        super(jpaRepository,entityManagerFactory);
    }

    @Override
    protected Category toDomain(CategoryEntity entity) {
        return  CategoryMapper.INSTANCE.toDomain(entity);
    }

    @Override
    protected CategoryEntity toEntity(Category domain) {
        return  CategoryMapper.INSTANCE.toEntity(domain);
    }
}
