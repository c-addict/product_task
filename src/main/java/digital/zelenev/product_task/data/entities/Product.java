package digital.zelenev.product_task.data.entities;

import digital.zelenev.product_task.annotations.InjectRandomInt;

import javax.persistence.*;

@Entity(
        name = "product"
)
@Table(
        name = "product"
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            unique = true,
            nullable = false
    )
    private Integer id;

    @Column(
            name = "name",
            unique = true,
            nullable = false
    )
    private String name;

    @Column(
            name = "price",
            nullable = false
    )
    @InjectRandomInt(min = 1, max = 5000)
    private Integer price;

    /**
     * No args constructor
     */
    public Product() {
    }

    /**
     * Full constructor
     * @param id
     * @param name
     * @param price
     */
    public Product(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
