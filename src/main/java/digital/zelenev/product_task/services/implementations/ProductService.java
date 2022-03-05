package digital.zelenev.product_task.services.implementations;

import digital.zelenev.product_task.data.dao.ProductRepository;
import digital.zelenev.product_task.data.dto.ProductDto;
import digital.zelenev.product_task.data.entities.Product;
import digital.zelenev.product_task.data.mappers.IMapper;
import digital.zelenev.product_task.exceptions.ResourceAlreadyExistsException;
import digital.zelenev.product_task.exceptions.ResourceNotFoundException;
import digital.zelenev.product_task.services.ICrudService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("productService")
@Transactional
public class ProductService implements ICrudService<ProductDto> {

    private final ProductRepository productRepository;
    private final IMapper<ProductDto, Product> toProductDtoMapper;
    private final IMapper<Product, ProductDto> toProductMapper;
    private final Environment environment;


    public ProductService(ProductRepository productRepository,
                          @Qualifier("toProductDtoMapper") IMapper<ProductDto, Product> toProductDtoMapper,
                          @Qualifier("toProductMapper") IMapper<Product, ProductDto> toProductMapper,
                          Environment environment
    ) {
        this.productRepository = productRepository;
        this.toProductDtoMapper = toProductDtoMapper;
        this.toProductMapper = toProductMapper;
        this.environment = environment;
    }

    @Override
    public List<ProductDto> readAll() {
        List<ProductDto> result = new LinkedList<>();
        for (Product product : productRepository.findAll())
            result.add(toProductDtoMapper.convert(product));
        return result;
    }

    @Override
    public ProductDto read(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            Product product = createRandomProduct(id);
            Product savedProduct = productRepository.save(product);
            return toProductDtoMapper.convert(savedProduct);
        } else {
            return toProductDtoMapper.convert(productOptional.get());
        }
    }

    @Override
    public void create(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
        if (optionalProduct.isPresent())
            throw new ResourceAlreadyExistsException(String.format("Product with id:%d already exists.", productDto.getId()));
        else {
            Product product = toProductMapper.convert(productDto);
            productRepository.save(product);
        }
    }

    @Override
    public void update(ProductDto productDto) {
        Optional<Product> productOptional = productRepository.findById(productDto.getId());
        if (productOptional.isEmpty())
            throw new ResourceNotFoundException(String.format("Product with id: %d doesn't exists.", productDto.getId()));
        else {
            productRepository.updateById(productDto.getId(), productDto.getName(), productDto.getPrice());
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty())
            throw new ResourceNotFoundException(String.format("Product with id: %d doesn't exists.", id));
        else
            productRepository.delete(productOptional.get());
    }

    private Product createRandomProduct(Integer id) {
        Product product = new Product();
        product.setName(String.format("Product %d", id));
        product.setPrice(getRandomProductPrice());
        return product;
    }

    private int getRandomProductPrice() {
        return RandomUtils.nextInt(
                Integer.parseInt(environment.getProperty("price.min")),
                Integer.parseInt(environment.getProperty("price.max"))
        );
    }
}
