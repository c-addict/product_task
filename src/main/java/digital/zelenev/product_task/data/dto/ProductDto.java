package digital.zelenev.product_task.data.dto;

public final class ProductDto {

    private final Integer id;
    private final String name;
    private final Integer price;

    public ProductDto(Integer id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
