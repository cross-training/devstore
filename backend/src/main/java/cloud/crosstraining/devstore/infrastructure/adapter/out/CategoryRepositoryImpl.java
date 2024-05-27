package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.application.port.out.CategoryRepository;
import cloud.crosstraining.devstore.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
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
    public CategoryRepositoryImpl(JpaCategoryRepository jpaCategoryRepository,
                                  EntityManagerFactory entityManagerFactory) {
        super(jpaCategoryRepository, entityManagerFactory);
    }

    public Set<CategoryEntity> getCategoryByNames(List<String> names) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Set<CategoryEntity> categories = new HashSet<>();
        try {
            TypedQuery<CategoryEntity> query = em.createQuery(
                    "SELECT c FROM Category c WHERE c.name IN :names",
                    CategoryEntity.class
            );
            query.setParameter("names", names);
            categories.addAll(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return categories;
    }

    @Override
    protected Category toDomain(CategoryEntity entity) {
        return  new Category(entity.getId(), entity.getName(), entity.getDescription());
    }

    @Override
    protected CategoryEntity toEntity(Category entity) {
        return new CategoryEntity(entity.getId(), entity.getName(), entity.getDescription());
    }
}
