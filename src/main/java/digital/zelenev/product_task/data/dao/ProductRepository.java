package digital.zelenev.product_task.data.dao;

import digital.zelenev.product_task.data.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Optional<Product> findById(Integer id);

    @Modifying
    @Query("UPDATE product p " +
            "SET p.name = ?2, p.price = ?3 " +
            "WHERE p.id = ?1")
    void updateById(Integer id, String name, Integer price);
}
