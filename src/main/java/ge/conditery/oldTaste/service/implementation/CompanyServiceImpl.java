package ge.conditery.oldTaste.service.implementation;

import ge.conditery.oldTaste.enums.AppErrorCode;
import ge.conditery.oldTaste.exception.AppException;
import ge.conditery.oldTaste.model.Company;
import ge.conditery.oldTaste.model.Location;
import ge.conditery.oldTaste.repository.CompanyRepository;
import ge.conditery.oldTaste.repository.LocationRepository;
import ge.conditery.oldTaste.service.CompanyService;
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
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final LocationRepository locationRepository;

    @Override
    public Company create(Company company) {
        try {
            companyRepository.save(company);
            log.info("Saving new Company: " + company.getCompanyName());
        } catch (Exception e) {
            log.info("Object is null!");
        }
        return companyRepository.save(company);
    }

    @Override
    public Collection<Company> companyList(int limit) {
        log.info("Find all Company");
        return companyRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Company get(int id) throws AppException {
        log.info("Find Company by Id: " + id);
        return companyRepository.findById(id)
                .orElseThrow(() -> new AppException("Company not found"));
    }

    @Override
    public Company update(Company company) {
        log.info("Update company: " + company.getCompanyName());
        return companyRepository.save(company);
    }

    @Override
    public Boolean delete(int id) {
        log.info("Delete company: " + id);
        try {
            companyRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Unable to delete " + AppErrorCode.COMPANY_NOT_EXIST);
        }

        return TRUE;
    }

    @Override
    public void relateLocation(String companyName, String address) {
        try {
            Company getCompany = companyRepository.getCompanyByCompanyName(companyName);
            Location getLocation = locationRepository.getLocationByAddress(address);

            getCompany.setLocation(getLocation);

            companyRepository.save(getCompany);
        } catch (Exception e) {
            log.info("Unable to relate location ");
        }
    }
}
