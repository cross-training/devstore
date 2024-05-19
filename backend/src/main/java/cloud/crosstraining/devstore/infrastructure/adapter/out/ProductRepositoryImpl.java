package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.application.port.out.ProductRepository;
import cloud.crosstraining.devstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl extends RepositoryImpl<Product,Long,ProductEntity> implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Autowired
    public ProductRepositoryImpl(JpaProductRepository jpaProductRepository) {
        super(jpaProductRepository);
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    protected Product toDomain(ProductEntity entity) {
        return new Product(entity.getId(),entity.getCode(), entity.getName(), entity.getPrice(), entity.getDescription(), entity.getCategories(), entity.getUrl());
    }

    @Override
    protected ProductEntity toEntity(Product product) {
        return new ProductEntity(product.getId(), product.getCode(), product.getName(), product.getPrice(), product.getDescription(), product.getCategories(), product.getUrl());
    }
}
