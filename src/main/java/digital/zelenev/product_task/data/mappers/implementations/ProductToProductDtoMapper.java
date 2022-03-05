package digital.zelenev.product_task.data.mappers.implementations;

import digital.zelenev.product_task.data.dto.ProductDto;
import digital.zelenev.product_task.data.entities.Product;
import digital.zelenev.product_task.data.mappers.BaseMapper;
import org.springframework.stereotype.Component;

@Component("toProductDtoMapper")
public class ProductToProductDtoMapper extends BaseMapper<ProductDto, Product> {

    @Override
    public ProductDto convert(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
