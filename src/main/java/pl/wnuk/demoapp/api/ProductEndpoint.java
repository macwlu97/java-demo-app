package pl.wnuk.demoapp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.wnuk.demoapp.domain.Product;
import pl.wnuk.demoapp.domain.ProductFacade;
import pl.wnuk.demoapp.domain.ProductRequestDto;
import pl.wnuk.demoapp.domain.ProductResponseDto;
import pl.wnuk.demoapp.infrastructure.ProductRepository;

import java.util.Optional;

@RestController
@RequestMapping("/products")
class ProductEndpoint {

    private final ProductFacade productFacade;

    @Autowired
    ProductEndpoint(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @PostMapping
    ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){
        return productFacade.create(productRequestDto);
    }

    @GetMapping("/{id}")
    ProductResponseDto getProduct(@PathVariable("id") String id){
        return productFacade.findById(id);
    }

    @PutMapping("/{id}")
    ProductResponseDto updateProduct(@PathVariable String id, @RequestBody ProductRequestDto productRequestDto){
        return  productFacade.update(id, productRequestDto);
    }

    @DeleteMapping("/{id}")
    ProductResponseDto deleteProduct(@PathVariable String id){
        return productFacade.delete(id);
    }

}
