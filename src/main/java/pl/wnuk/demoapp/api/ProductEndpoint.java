package pl.wnuk.demoapp.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wnuk.demoapp.domain.ProductFacade;
import pl.wnuk.demoapp.domain.ProductRequestDto;
import pl.wnuk.demoapp.domain.ProductResponseDto;

@RestController
@RequestMapping("/products")
class ProductEndpoint {

    public ProductEndpoint(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    private final ProductFacade productFacade;



    @PostMapping
    ProductResponseDto createProduct(ProductRequestDto productRequestDto){
        return productFacade.create(productRequestDto);
    }
}
