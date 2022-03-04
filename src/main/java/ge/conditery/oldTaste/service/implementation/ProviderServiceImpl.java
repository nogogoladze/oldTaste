package ge.conditery.oldTaste.service.implementation;

import ge.conditery.oldTaste.enums.AppErrorCode;
import ge.conditery.oldTaste.model.Company;
import ge.conditery.oldTaste.model.Provider;
import ge.conditery.oldTaste.repository.CompanyRepository;
import ge.conditery.oldTaste.repository.ProviderRepository;
import ge.conditery.oldTaste.service.ProviderService;
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
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;

    private final CompanyRepository companyRepository;

    @Override
    public Provider create(Provider provider) {
        log.info("Saving new provider: " + provider.getContactPerson());
        return providerRepository.save(provider);
    }

    @Override
    public Collection<Provider> providerList(int limit) {
        log.info("Find all provider");
        return providerRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Provider get(int id) {
        log.info("Find provider by Id: " + id);
        return providerRepository.findById(id).get();
    }

    @Override
    public Provider update(Provider provider) {
        log.info("Update provider: " + provider.getContactPerson());
        return providerRepository.save(provider);
    }

    @Override
    public Boolean delete(int id) {
        log.info("Delete provider: " + id);
        try {
            providerRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Unable to delete " + AppErrorCode.PROVIDER_NOT_EXIST);
        }

        return TRUE;
    }

    @Override
    public void relateCompany(Integer providerId, String companyName) {
        try {
            Provider getProvider = providerRepository.getById(providerId);
            Company getCompany = companyRepository.getCompanyByCompanyName(companyName);

            getProvider.setCompany(getCompany);

            providerRepository.save(getProvider);
            log.info("relate company ");

        } catch (Exception e) {
            log.info("Unable to relate company ");
        }

    }
}
