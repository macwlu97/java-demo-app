package pl.wnuk.demoapp.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.wnuk.demoapp.domain.Product;
import pl.wnuk.demoapp.domain.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

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
    public Product update(Product product, String name) {
        if(!products.containsKey(product.getId())) throw new ProductNotFoundException("Aktualizacja danych się nie powiodła, nie znaleziono produktu!");
        products.put(product.getId(), new Product(product.getId(), name, product.getCreatedAt()));
        return products.get(product.getId());
    }

    @Override
    public void delete(String id) {
        if(!products.containsKey(id)) throw new ProductNotFoundException("Usunięcie produktu się nie powiodło, ponieważ, " +
                "nie znaleziono produktu!");
        products.remove(id);
    }

}
