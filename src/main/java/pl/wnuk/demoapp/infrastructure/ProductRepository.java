package pl.wnuk.demoapp.infrastructure;

import pl.wnuk.demoapp.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {

    void save(Product product);

    Product read(String id);

    List<Product> readList();

    Product update(Product product, String name);
    Product update(Product product, String amount, String currency);
    Product update(Product product, String name, String amount, String currency);

    void delete(String id);
}
