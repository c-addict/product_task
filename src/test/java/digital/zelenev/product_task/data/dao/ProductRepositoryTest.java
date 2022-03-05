package digital.zelenev.product_task.data.dao;

import digital.zelenev.product_task.data.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindByIdIfExists() {
        Product product = new Product();
        product.setName("Product");
        product.setPrice(1500);
        Product savedProduct = productRepository.save(product);

        Optional<Product> found = productRepository.findById(savedProduct.getId());

        assertThat(found.isPresent()).isTrue();
    }

    @Test
    public void testFindByIdIfDoesNotExists() {

        Optional<Product> found = productRepository.findById(1);

        assertThat(found.isEmpty()).isTrue();
    }
}
