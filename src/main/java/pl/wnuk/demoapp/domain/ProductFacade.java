package pl.wnuk.demoapp.domain;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Map;

public interface ProductFacade {

    ProductResponseDto create(ProductRequestDto productRequest);

    ProductResponseDto read(String id);

    ProductsResponseDto  readList();

    ProductResponseDto update(String id, ProductRequestDto productRequestDto);

    ResponseEntity<Void> delete(String id);
}
