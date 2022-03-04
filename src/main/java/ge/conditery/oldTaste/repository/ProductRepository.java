package ge.conditery.oldTaste.repository;

import ge.conditery.oldTaste.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
