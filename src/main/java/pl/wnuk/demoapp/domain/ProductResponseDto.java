package pl.wnuk.demoapp.domain;

public class ProductResponseDto {
    public ProductResponseDto(String id, String name) {
    }

    public String getId() {
        return id;
    }

    private final String id;

    public String getName() {
        return name;
    }

    private final String name;
}
