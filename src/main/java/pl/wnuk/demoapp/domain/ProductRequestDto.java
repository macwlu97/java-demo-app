package pl.wnuk.demoapp.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestDto {

    private final String name;
    private final PriceDto price;
    //json
    @JsonCreator
    public ProductRequestDto(@JsonProperty("name") String name,
                             @JsonProperty("price") PriceDto price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public PriceDto getPrice() {
        return price;
    }

    public boolean isValidToCreate(){
        return name != null && !name.equals("") && price != null
                && !price.getAmount().equals("") && !price.getCurrency().equals("");
    }

    public boolean isValidToUpdate(){
        return (name != null && !name.equals("")) || (price != null
                && !price.getAmount().equals("") && !price.getCurrency().equals(""));
    }

    @Override
    public String toString() {
        return "ProductRequestDto{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}