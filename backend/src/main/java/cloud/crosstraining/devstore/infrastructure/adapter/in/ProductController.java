package cloud.crosstraining.devstore.infrastructure.adapter.in;

import cloud.crosstraining.devstore.application.port.in.ProductService;
import cloud.crosstraining.devstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController extends Controller<Product, Long> {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }

}
