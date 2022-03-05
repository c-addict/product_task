package digital.zelenev.product_task.data.dao;

import digital.zelenev.product_task.data.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Optional<Product> findById(Integer id);

}
