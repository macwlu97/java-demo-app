package pl.wnuk.demoapp.domain;

public class ProductResponseDto {

    private final String id;
    private final String name;


    public ProductResponseDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}