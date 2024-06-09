package cloud.crosstraining.devstore.catalog.infrastructure.adapter.out;

import cloud.crosstraining.devstore.catalog.application.port.out.ProductRepository;
import cloud.crosstraining.devstore.catalog.domain.Product;
import cloud.crosstraining.devstore.common.infrastructure.adapter.out.RepositoryImpl;
import cloud.crosstraining.devstore.common.domain.FindAllArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import jakarta.persistence.EntityManagerFactory;
import java.util.*;

@Slf4j
@Repository
public class ProductRepositoryImpl extends RepositoryImpl<Product,Long,ProductEntity> implements ProductRepository {

    private final CategoryRepositoryImpl categoryRepository;

    @Override
    protected Class<ProductEntity> classEntity() {
        return ProductEntity.class;
    }

    @Autowired
    public ProductRepositoryImpl(
            ProductJpaRepository jpaRepository,
            EntityManagerFactory entityManagerFactory,
            CategoryRepositoryImpl categoryRepository
    ) {
        super(jpaRepository,entityManagerFactory);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Mono<Product> findById(Long id) {
        ProductEntity entity = _findById(id);
        if (entity!= null) {
            // force load category
            entity.getCategory();
            return Mono.just(toDomain(entity));
        }
        return null;
    }

    @Override
    public Flux<Product> findAll(FindAllArgs args) {
        Set<ProductEntity> entities = _findAll(args);
        if(Arrays.stream(args.getIncludes()).anyMatch(p -> p.toLowerCase() == "category")) {
            entities.stream().forEach(p-> p.getCategory());
        }
        return Flux.fromIterable(entities.stream().map(this::toDomain).toList());
    }

    @Override
    protected Product toDomain(ProductEntity entity) {
        Product domain = ProductMapper.INSTANCE.toDomain(entity);
        domain.setCategory(CategoryMapper.INSTANCE.toDomain(entity.getCategory()));
        return domain;
    }

    @Override
    protected ProductEntity toEntity(Product domain) {
        ProductEntity entity = ProductMapper.INSTANCE.toEntity(domain);
        entity.setCategory(CategoryMapper.INSTANCE.toEntity(domain.getCategory()));
        return entity;
    }
}
