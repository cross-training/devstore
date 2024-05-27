package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.application.port.out.ProductRepository;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import cloud.crosstraining.devstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl extends RepositoryImpl<Product,Long,ProductEntity> implements ProductRepository {

    private final CategoryRepositoryImpl categoryRepository;

    @Override
    protected Class<ProductEntity> classEntity() {
        return ProductEntity.class;
    }

    @Autowired
    public ProductRepositoryImpl(
            JpaProductRepository jpaRepository,
            EntityManagerFactory entityManager,
            CategoryRepositoryImpl categoryRepository
    ) {
        super(jpaRepository,entityManager);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Mono<Product> findById(Long id) {
        ProductEntity entity = _findById(id);
        if (entity!= null) {
            // force get children categories
            entity.getCategories().size();
            return Mono.just(toDomain(entity));
        }
        return null;
    }

    @Override
    public Flux<Product> findAll(FindAllArgs args) {
        Set<ProductEntity> entities = _findAll(args);
        if(Arrays.stream(args.getIncludes()).anyMatch(p -> p.toLowerCase() == "categories")) {
            entities.stream().forEach(p-> p.getCategories().size());
        }
        return Flux.fromIterable(entities.stream().map(this::toDomain).toList());
    }

    @Override
    public Mono<Product> save(Product entity) {
        ProductEntity current= null;
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Set<CategoryEntity> categories = categoryRepository.getCategoryByNames(entity.getCategories());
            current = _findById(entity.getId());
            if (current != null) {
                current.getCategories().size();
                // Add category if not exists
                for(CategoryEntity category: categories ) {
                    Optional<CategoryEntity> currentCategory = current.getCategories().stream().filter(p-> p.getName() == category.getName()).findFirst();
                    if (currentCategory.isEmpty()) {
                        current.getCategories().add(category);
                    }
                }
                // Remove categories
                List<String> namesToRemove = new ArrayList<>();
                for(CategoryEntity category: current.getCategories() ) {
                    if(!categories.stream().anyMatch(p-> p.getName() == category.getName())) {
                        namesToRemove.add(category.getName());
                    }
                }
                for(String nameToRemove: namesToRemove) {
                    Optional<CategoryEntity> category =  current.getCategories().stream().filter(p-> p.getName()==nameToRemove).findFirst();
                    if(!category.isEmpty()) {
                        current.getCategories().remove(category.get());
                    }
                }
            } else {
                current = toEntity(entity,categories);
            }
            em.persist(current);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return Mono.just(toDomain(current));
    }

    @Override
    protected Product toDomain(ProductEntity entity) {
        Product product =  new Product(entity.getId(), entity.getName(), entity.getPrice(), entity.getDescription(), new ArrayList<String>(), entity.getUrl(),entity.getReview(), entity.getImageUrl());
        product.getCategories().addAll(entity.getCategories().stream().map(p-> p.getName()).collect(Collectors.toList()));
        return product;
    }

    @Override
    protected ProductEntity toEntity(Product product) {
        return toEntity(product, null);
    }

    protected ProductEntity toEntity(Product product,Set<CategoryEntity> categories) {
        ProductEntity entity = new ProductEntity(product.getId(), product.getName(), product.getPrice(), product.getDescription(),product.getReviews() , null, product.getUrl(), product.getImageUrl());
        if (categories!=null) {
            entity.setCategories(categories);
        }
        return entity;
    }
}
