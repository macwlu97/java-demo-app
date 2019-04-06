package pl.wnuk.demoapp.domain;

import org.springframework.http.ResponseEntity;

public interface ProductFacade {

    ProductResponseDto create(ProductRequestDto productRequest);

    ProductResponseDto read(String id);

    ProductResponseDto update(String id, ProductRequestDto productRequestDto);

    ResponseEntity<Void> delete(String id);
}
