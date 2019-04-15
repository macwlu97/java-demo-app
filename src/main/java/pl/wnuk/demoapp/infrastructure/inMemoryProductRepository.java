package pl.wnuk.demoapp.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.wnuk.demoapp.domain.Price;
import pl.wnuk.demoapp.domain.Product;
import pl.wnuk.demoapp.domain.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Repository
public class inMemoryProductRepository implements ProductRepository {

    private final Map<String, Product> products = new HashMap<>();

    @Override
    public void save(Product product){
        products.put(product.getId(), product);
    }

    @Override
    public Product read(String id) {
        if(!products.containsKey(id)) throw new ProductNotFoundException("Nie znaleziono produktu!");
        return products.get(id);
    }

    @Override
    public List<Product> readList() {
        return products.values()
                .stream()
                .sorted(Comparator.comparing(Product::getCreatedAt))
                .collect(toList());
    }

    @Override
    public Product update(Product product, String name) {
        if(products.containsKey(product.getId())){
            Product newProduct = new Product(product.getId(), name, product.getPrice(), product.getCreatedAt());
            products.replace(product.getId(), newProduct);
            return newProduct;
        }else{
            throw new ProductNotFoundException("Nie można zaaktualizować produktu!");
        }
    }

    public Product update(Product product, String amount, String currency) {
        if(products.containsKey(product.getId())){
            Price newPrice = new Price(new BigDecimal(amount), Currency.getInstance(currency));
            Product newProduct = new Product(product.getId(), product.getName(), newPrice, product.getCreatedAt());
            products.replace(product.getId(), newProduct);
            return newProduct;
        }else{
            throw new ProductNotFoundException("Nie można zaaktualizować produktu!");
        }
    }

    public Product update(Product product, String name, String amount, String currency) {
        if(products.containsKey(product.getId())){
            Price newPrice = new Price(new BigDecimal(amount), Currency.getInstance(currency));
            Product newProduct = new Product(product.getId(), name, newPrice, product.getCreatedAt());
            products.replace(product.getId(), newProduct);
            return newProduct;
        }else{
            throw new ProductNotFoundException("Nie można zaaktualizować produktu!");
        }
    }

    @Override
    public void delete(String id) {
        if(!products.containsKey(id)) throw new ProductNotFoundException("Usunięcie produktu się nie powiodło, ponieważ, " +
                "nie znaleziono produktu!");
        products.remove(id);
    }

}
