package pl.wnuk.demoapp.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequestDto {

    private final String name;

    @JsonCreator
    public ProductRequestDto(@JsonProperty("name") String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                '}';
    }


    public boolean isValid(){
        //true &&
        return name != null && !name.isBlank();
    }


}
