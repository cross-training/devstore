package cloud.crosstraining.devstore.catalog.application.port.out;
import cloud.crosstraining.devstore.catalog.domain.Product;
import cloud.crosstraining.devstore.common.application.port.out.Repository;

public interface ProductRepository extends Repository<Product, Long> {
}
