package cloud.crosstraining.devstore.product.service;

import cloud.crosstraining.devstore.product.entity.Category;
import cloud.crosstraining.devstore.product.entity.Product;

import javax.sound.sampled.Port;
import java.util.List;

public interface ProductService {
    public List<Product> list();
    public List<Product> findByCategory(Category category);
    public Product find(Long id);
    public Product create(Product product);
    public Product update(Product product);
    public void delete(Long id);
    public Product incrementStock(Long id, Double quantity);
}
