package digital.zelenev.product_task.data.mappers.implementations;

import digital.zelenev.product_task.data.dto.ProductDto;
import digital.zelenev.product_task.data.entities.Product;
import digital.zelenev.product_task.data.mappers.BaseMapper;
import org.springframework.stereotype.Component;

@Component("toProductMapper")
public class ProductDtoToProductMapper extends BaseMapper<Product, ProductDto> {

    @Override
    public Product convert(ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice()
        );
    }
}
