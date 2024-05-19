package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.application.port.out.CategoryRepository;
import cloud.crosstraining.devstore.domain.Category;
import cloud.crosstraining.devstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryImpl extends RepositoryImpl<Category,Long,CategoryEntity>  implements CategoryRepository {


    @Autowired
    public CategoryRepositoryImpl(JpaCategoryRepository categoryRepository,EntityManagerFactory entityManager) {
        super(categoryRepository, entityManager);
    }

    public List<CategoryEntity> getCategoryByNames(List<String> names) {
        EntityManager em = entityManager.createEntityManager();
        Set<CategoryEntity> categories = new HashSet<>();
        try {
            em.getTransaction().begin();
            TypedQuery<CategoryEntity> query = em.createQuery(
                    "SELECT c FROM Category c WHERE c.name IN :names",
                    CategoryEntity.class
            );
            query.setParameter("names", names);
            categories.addAll(query.getResultList());
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return categories.stream().collect(Collectors.toList());
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
