package cloud.crosstraining.devstore.product;

import cloud.crosstraining.devstore.product.entity.Category;
import cloud.crosstraining.devstore.product.entity.Product;
import cloud.crosstraining.devstore.product.repository.ProductRepository;
import cloud.crosstraining.devstore.product.service.ProductService;
import cloud.crosstraining.devstore.product.service.ProductServiceImpl;
import com.sun.source.tree.ModuleTree;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository repository;

    private ProductService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new ProductServiceImpl(repository);

        Product product1 = Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("2343.69"))
                .status("created")
                .createAt(new Date())
                .build();

        Mockito.when(repository.findById(1L))
               .thenReturn(Optional.of(product1));
        Mockito.when(repository.save(product1)).thenReturn(product1);
    }

    @Test
    public void whenValidateId_thenReturnProduct1() {
        Product found = service.find(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }

    @Test
    public void whenValidateIncrementStock_thenStockIsUpdate() {
        Product newStock = service.incrementStock(1L,Double.parseDouble("-5"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(Double.parseDouble("5"));
    }
}
