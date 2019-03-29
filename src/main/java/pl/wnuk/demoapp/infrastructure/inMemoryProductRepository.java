package pl.wnuk.demoapp.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.wnuk.demoapp.domain.Product;

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
    public Product findById(String id) {
        return products.get(id);
    }

    ;
}
