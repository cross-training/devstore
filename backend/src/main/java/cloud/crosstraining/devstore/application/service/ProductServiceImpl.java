package cloud.crosstraining.devstore.application.service;

import cloud.crosstraining.devstore.application.port.in.ProductService;
import cloud.crosstraining.devstore.application.port.out.ProductRepository;
import cloud.crosstraining.devstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<Product,Long> implements ProductService {

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
    }
}
