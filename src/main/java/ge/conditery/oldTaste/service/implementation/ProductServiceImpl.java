package ge.conditery.oldTaste.service.implementation;

import ge.conditery.oldTaste.enums.AppErrorCode;
import ge.conditery.oldTaste.model.Company;
import ge.conditery.oldTaste.model.Product;
import ge.conditery.oldTaste.model.Provider;
import ge.conditery.oldTaste.repository.CompanyRepository;
import ge.conditery.oldTaste.repository.ProductRepository;
import ge.conditery.oldTaste.repository.ProviderRepository;
import ge.conditery.oldTaste.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProviderRepository providerRepository;

    private final CompanyRepository companyRepository;

    @Override
    public Product create(Product product) {
        log.info("Saving new product: " + product.getProductName());
        return productRepository.save(product);
    }

    @Override
    public Collection<Product> productList(int limit) {
        log.info("Find all product");
        return productRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Product get(int id) {
        log.info("Find product by Id: " + id);
        return productRepository.findById(id).get();
    }

    @Override
    public Product update(Product product) {
        log.info("Update product: " + product.getProductName());
        return productRepository.save(product);
    }

    @Override
    public void relateProvider(Integer productId, Integer providerId) {
        try {
            Product getProduct = productRepository.getById(productId);
            Provider getProvider = providerRepository.getById(providerId);

            getProduct.getProviders().add(getProvider);

            productRepository.save(getProduct);
            log.info("relate provider ");

        } catch (Exception e) {
            log.info("Unable to relate provider ");
        }
    }

    @Override
    public void relateCompany(Integer productId, String companyName) {
        try {
            Product getProduct = productRepository.getById(productId);
            Company getCompany = companyRepository.getCompanyByCompanyName(companyName);

            getProduct.getCompanies().add(getCompany);

            productRepository.save(getProduct);
            log.info("relate company ");

        } catch (Exception e) {
            log.info("Unable to relate company");
        }
    }

    @Override
    public Boolean delete(int id) {
        log.info("Delete product: " + id);
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Unable to delete " + AppErrorCode.PRODUCT_NOT_EXIST);
        }

        return TRUE;
    }


}
