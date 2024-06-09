package cloud.crosstraining.devstore.catalog.application.service;

import cloud.crosstraining.devstore.catalog.application.port.out.ProductRepository;
import cloud.crosstraining.devstore.catalog.domain.Product;
import cloud.crosstraining.devstore.common.application.service.Service;
//import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ProductService extends Service<Product,Long> {

    @Autowired
    public ProductService(ProductRepository productRepository) {
        super(productRepository);
    }

    public void adjustStock(Long id, int quantity) {
        Product product = repository.findById(id).block();
        if (product != null) {
            String message = String.format("Product %d not found", id);
//            log.warn(message);
            throw new IllegalArgumentException(message);
        }
        Double newStock = product.getStock() + quantity;
        if (newStock < 0) {
            String message = "Stock cannot be negative";
//            log.error(message);
            throw new IllegalArgumentException(message);
        }
        product.setStock(newStock);
    }
}
