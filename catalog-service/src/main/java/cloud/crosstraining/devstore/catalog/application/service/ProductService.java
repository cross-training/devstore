package cloud.crosstraining.devstore.catalog.application.service;

import cloud.crosstraining.devstore.catalog.application.port.out.ProductRepository;
import cloud.crosstraining.devstore.catalog.domain.Product;
import cloud.crosstraining.devstore.common.application.service.Service;
//import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

//@Log4j
@org.springframework.stereotype.Service
public class ProductService extends Service<Product,Long> {

    @Autowired
    public ProductService(ProductRepository productRepository) {
        super(productRepository);
    }

    public Mono<Double> adjustStock(Long id, Double quantity) {
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
        return repository.save(product).map( p-> p.getStock() );
    }

    public Mono<Double> rating(Long id, Double rating) {
        Product product = repository.findById(id).block();
        if (product != null) {
            String message = String.format("Product %d not found", id);
            throw new IllegalArgumentException(message);
        }
        if (rating < 0 || rating > 5) {
            String message = String.format("Rating %d is minor that 0 or major that 5",rating);
            throw new IllegalArgumentException(message);
        }
        Long newWReviews = product.getReviews() + 1;
        Double newRating = ((product.getRating() * product.getReviews()) + rating) / newWReviews;
        product.setRating(newRating);
        product.setReviews(newWReviews);
        return repository.save(product).map( p-> p.getRating());
    }
}
