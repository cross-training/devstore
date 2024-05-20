package cloud.crosstraining.devstore.application.port.out;
import cloud.crosstraining.devstore.domain.Product;
import cloud.crosstraining.devstore.infrastructure.adapter.out.ProductEntity;

public interface ProductRepository extends  Repository<Product, Long> {
}
