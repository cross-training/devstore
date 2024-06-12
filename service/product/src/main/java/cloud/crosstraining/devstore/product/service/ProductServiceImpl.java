package cloud.crosstraining.devstore.product.service;

import cloud.crosstraining.devstore.product.entity.Category;
import cloud.crosstraining.devstore.product.entity.Product;
import cloud.crosstraining.devstore.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl  implements  ProductService{

    private final ProductRepository repository;

    @Override
    public List<Product> list() {
        return repository.findAll();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return repository.findByCategory(category);
    }

    @Override
    public Product find(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product p = find(product.getId());
        if( p==null){
            return null;
        }
        p.setName(product.getName());
        p.setDescription(product.getDescription());
        p.setCategory(product.getCategory());
        p.setPrice(product.getPrice());
        return repository.save(p);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product incrementStock(Long id, Double quantity) {
        Product product = repository.findById(id).orElse(null);
        if ( product == null ) {
            return null;
        }
        product.setStock(product.getStock()+quantity);
        return repository.save(product);
    }
}
