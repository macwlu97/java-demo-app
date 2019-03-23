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

//        ProductResponseDto respone = new ProductResponseDto();
        //stworzyć produkt
        //zapisać go gdzieś
        //przemapować Product -> ProductResponseDto i zwrócić

        return new ProductResponseDto(
                product.getId(),
                product.getName()
        );
    }
}
