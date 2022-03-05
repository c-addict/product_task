package digital.zelenev.product_task.controllers.implementations;

import digital.zelenev.product_task.controllers.ICrudController;
import digital.zelenev.product_task.data.dto.ProductDto;
import digital.zelenev.product_task.services.ICrudService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "/api/v1/product"
)
public class ProductController implements ICrudController<ProductDto> {

    private final ICrudService<ProductDto> productService;

    public ProductController(@Qualifier("productService") ICrudService<ProductDto> productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping(path = "/all")
    public List<ProductDto> readAll() {
        return productService.readAll();
    }

    @Override
    @GetMapping(path = "/{id}")
    public ProductDto read(@PathVariable(name = "id") Integer id) {
        return productService.read(id);
    }

    @Override
    @PostMapping
    public void create(@RequestBody ProductDto productDto) {
        productService.create(productDto);
    }

    @Override
    @PutMapping
    public void update(@RequestBody ProductDto productDto) {
        productService.update(productDto);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        productService.delete(id);
    }
}
