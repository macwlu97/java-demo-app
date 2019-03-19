package pl.wnuk.demoapp.domain;

public class ProductRequestDto {

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                '}';
    }

    private final String name;

    private ProductRequestDto(String name){
        this.name = name;
    }

    public boolean isValid(){
        return name != null && !name.isBlank();
    }


}
