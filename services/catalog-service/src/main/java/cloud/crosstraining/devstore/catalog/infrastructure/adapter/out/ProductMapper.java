package cloud.crosstraining.devstore.catalog.infrastructure.adapter.out;

import cloud.crosstraining.devstore.catalog.domain.Product;

public class ProductMapper {
    public static ProductMapper INSTANCE = new ProductMapper();

    public ProductEntity toEntity(Product source) {
        if (source == null) {
            return null;
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(source.getId());
        productEntity.setName(source.getName());
        productEntity.setPrice(source.getPrice());
        productEntity.setDescription(source.getDescription());
//        productEntity.setReview(source.getReview());
        productEntity.setCategory(CategoryMapper.INSTANCE.toEntity(source.getCategory()));
        productEntity.setUrl(source.getUrl());
        productEntity.setImageUrl(source.getImageUrl());
        productEntity.setCreateAt(source.getCreateAt());
        return productEntity;
    }

    public Product toDomain(ProductEntity source) {
        if (source == null) {
            return null;
        }

        Product product = new Product();
        product.setId(source.getId());
        product.setName(source.getName());
        product.setPrice(source.getPrice());
        product.setDescription(source.getDescription());
//        product.setReview(source.getReview());
        product.setCategory(CategoryMapper.INSTANCE.toDomain(source.getCategory()));
        product.setUrl(source.getUrl());
        product.setImageUrl(source.getImageUrl());
        product.setCreateAt(source.getCreateAt());

        return product;
    }
}