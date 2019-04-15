package pl.wnuk.demoapp.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.wnuk.demoapp.exceptions.BadRequestException;
import pl.wnuk.demoapp.infrastructure.ProductRepository;

import java.math.BigDecimal;
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
        if(!productRequest.isValidToCreate()){
            throw new BadRequestException();
        }
        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        Price price = new Price(new BigDecimal(productRequest.getPrice().getAmount()),
                Currency.getInstance(productRequest.getPrice().getCurrency()));
        Product product = new Product(id, productRequest.getName(), price, createdAt);
        productRepository.save(product);
        return new ProductResponseDto(product.getId(), product.getName(), new PriceDto(price));
    }

    @Override
    public ProductResponseDto read(String id){
        Product product = productRepository.read(id);
        PriceDto priceDto = new PriceDto(product.getPrice().getAmount().toString(),
                product.getPrice().getCurrency().getCurrencyCode());
        return new ProductResponseDto(product.getId(), product.getName(), priceDto);
    }

    @Override
    public ProductsResponseDto readList() {
        List<Product> allProducts = productRepository.readList();
        List<ProductResponseDto> response = allProducts.stream().map(product -> new ProductResponseDto(product.getId(),
                product.getName(), new PriceDto(product.getPrice())))
                .sorted(Comparator.comparing(ProductResponseDto::getId)).collect(Collectors.toList());

        return new ProductsResponseDto(response);
    }

    @Override
    public ProductResponseDto update(String id, ProductRequestDto productRequestDto) {
        if(!productRequestDto.isValidToUpdate()){
            throw new BadRequestException();
        }
        Product product = productRepository.read(id);
        Product updatedProduct;
        if(productRequestDto.getName() != null && productRequestDto.getPrice() != null){
            updatedProduct = productRepository.update(product, productRequestDto.getName(),
                    productRequestDto.getPrice().getAmount(), productRequestDto.getPrice().getCurrency());
        }else if(productRequestDto.getName() != null){
            updatedProduct = productRepository.update(product, productRequestDto.getName());
        }else {
            updatedProduct = productRepository.update(product, productRequestDto.getPrice().getAmount(),
                    productRequestDto.getPrice().getCurrency());
        }
        return new ProductResponseDto(updatedProduct.getId(), updatedProduct.getName(), new PriceDto(updatedProduct.getPrice()));
    }


    @Override
    public ResponseEntity<Void> delete(String id) {
        productRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
