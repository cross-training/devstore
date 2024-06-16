package cloud.crosstraining.devstore.product;

import cloud.crosstraining.devstore.product.entity.Category;
import cloud.crosstraining.devstore.product.entity.Product;
import cloud.crosstraining.devstore.product.repository.CategoryRepository;
import cloud.crosstraining.devstore.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void whenFindByCategory_ThenReturnListProducts(){

        Category category01 = Category.builder().id(1L).name("Informatics") .build();
        Category category02 = Category.builder().id(2L).name("Electronics") .build();
        categoryRepository.save(category01);
        categoryRepository.save(category02);

        Product product1 = Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("2343.69"))
                .status("created")
                .createAt(new Date())
                .build();

        productRepository.save(product1);
        List<Product> products = productRepository.findByCategory( product1.getCategory());
        Assertions.assertThat(products.size()).isEqualTo(1);
    }
}
