package pl.wnuk.demoapp.domain;

import org.springframework.http.ResponseEntity;

public interface ProductFacade {

    //CRUD

    //Create
    ProductResponseDto create(ProductRequestDto productRequest);

    //Read
    ProductResponseDto read(String id);

    //Update
    ProductResponseDto update(String id, ProductRequestDto productRequestDto);

    //Delete
    ResponseEntity delete(String id);
}
