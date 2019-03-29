package pl.wnuk.demoapp.domain;

public interface ProductFacade {

    //get
//    ProductResponseDto get(String id);
    //create

    ProductResponseDto create(ProductRequestDto productRequest);

    ProductResponseDto findById(String id);

    //update
    //delete
}
