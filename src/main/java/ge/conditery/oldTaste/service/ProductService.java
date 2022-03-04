package ge.conditery.oldTaste.service;

import ge.conditery.oldTaste.model.Product;

import java.util.Collection;

public interface ProductService {
    Product create(Product product);

    Collection<Product> productList(int limit);

    Product get(int id);

    Product update(Product product);

    Boolean delete(int id);

    void relateProvider(Integer productId, Integer providerId);

    void relateCompany(Integer productId, String companyName);
}
