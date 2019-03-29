package pl.wnuk.demoapp.infrastructure;

import pl.wnuk.demoapp.domain.Product;

public interface ProductRepository {

    void save(Product product);

    Product findById(String id);

    Product update(Product product, String name);

    void delete(String id);
}
