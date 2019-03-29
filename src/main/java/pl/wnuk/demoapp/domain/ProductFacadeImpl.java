package pl.wnuk.demoapp.domain;

import org.springframework.stereotype.Component;
import pl.wnuk.demoapp.infrastructure.ProductRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ProductFacadeImpl implements ProductFacade {
    public ProductFacadeImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto create(ProductRequestDto productRequest) {
        //walidacja
        if (!productRequest.isValid()){
            throw new RuntimeException("Product name cannot be empty!");
        }

        //towrzenie
        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = new Product(id, productRequest.getName(), createdAt);

        //zapis
        productRepository.save(product);

        return new ProductResponseDto(
                product.getId(),
                product.getName()
        );
    }

    @Override
    public ProductResponseDto findById(String id){
        Product product = productRepository.findById(id);
        return new ProductResponseDto(product.getId(), product.getName());
    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto productRequest) {
        if (!productRequest.isValid()){
            throw new RuntimeException("Product name cannot be empty!");
        }

        Product product = productRepository.findById(id);
        Product updatedProduct = productRepository.update(product, productRequest.getName());

        return new ProductResponseDto(updatedProduct.getId(), updatedProduct.getName());
    }

    @Override
    public ProductResponseDto delete(String id) {
        Product product = productRepository.findById(id);
        productRepository.delete(id);
        return new ProductResponseDto(product.getId(), product.getName());
    }


}
