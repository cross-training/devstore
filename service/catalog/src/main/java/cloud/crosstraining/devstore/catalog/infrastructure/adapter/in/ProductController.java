package cloud.crosstraining.devstore.catalog.infrastructure.adapter.in;

import cloud.crosstraining.devstore.catalog.application.service.ProductService;
import cloud.crosstraining.devstore.catalog.domain.Product;
import cloud.crosstraining.devstore.common.infrastructure.adapter.in.Controller;
import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/products")
public class ProductController extends Controller<Product, Long> {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }

    @PatchMapping("/{id}/adjustStock")
    public Mono<Double> adjustStock(@PathVariable(name="id") Long id,@QueryParam("quantity") Double quantity) {
        return productService.adjustStock(id,quantity);
    }

    @PatchMapping("/{id}/rating")
    public Mono<Double> rating(@PathVariable(name="id")  Long id,@QueryParam("rating") Double rating) {
        return productService.rating(id, rating);
    }


}
