package pl.wnuk.demoapp.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.wnuk.demoapp.infrastructure.ProductRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
class ProductFacadeImpl implements ProductFacade {

    private final ProductRepository productRepository;

    ProductFacadeImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public ProductResponseDto create(ProductRequestDto productRequest) {

        if (!productRequest.isValid()){
            throw new RuntimeException("Product name cannot be empty!");
        }


        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = new Product(id, productRequest.getName(), createdAt);


        productRepository.save(product);

        return new ProductResponseDto(
                product.getId(),
                product.getName()
        );
    }

    @Override
    public ProductResponseDto read(String id){
        Product product = productRepository.read(id);
        return new ProductResponseDto(product.getId(), product.getName());
    }

    @Override
    public ProductsResponseDto readList() {
        List<Product> products = productRepository.readList();
        List<ProductResponseDto> productsResponse = products.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
        return new ProductsResponseDto(productsResponse);
    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto productRequest) {
        if (!productRequest.isValid()){
            throw new RuntimeException("Product name cannot be empty!");
        }

        Product product = productRepository.read(id);
        Product updatedProduct = productRepository.update(product, productRequest.getName());

        return new ProductResponseDto(updatedProduct.getId(), updatedProduct.getName());
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        productRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
