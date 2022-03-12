package ge.conditery.oldTaste.controller;

import ge.conditery.oldTaste.model.Product;
import ge.conditery.oldTaste.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class ProductController {
    private final ProductService productService;

    @GetMapping("product")
    public Collection<Product> getAllCar() {
        return productService.productList(10);
    }

    @PostMapping("product/save")
    public Product saveCar(@RequestBody Product product) {
        return productService.create(product);
    }

    @PostMapping("product/provider/{productId}/{providerId}")
    public void relateProvider(@PathVariable Integer productId,
                               @PathVariable Integer providerId) {
        productService.relateProvider(productId, providerId);
    }

    @PostMapping("product/company/{productId}/{companyName}")
    public void relateCompany(@PathVariable Integer productId,
                              @PathVariable String companyName) {
        productService.relateCompany(productId, companyName);
    }


    @DeleteMapping("product/delete/{id}")
    public void deleteCar(@PathVariable Integer id) {
        try {
            productService.delete(id);
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
