package pl.wnuk.demoapp.domain;

public interface ProductFacade {

    //CRUD

    //Create
    ProductResponseDto create(ProductRequestDto productRequest);

    //Read
    ProductResponseDto findById(String id);

    //Update
    ProductResponseDto update(String id, ProductRequestDto productRequestDto);

    //Delete
    ProductResponseDto delete(String id);
}
