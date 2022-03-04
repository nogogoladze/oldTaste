package ge.conditery.oldTaste.service;

import ge.conditery.oldTaste.exception.AppException;
import ge.conditery.oldTaste.model.Company;

import java.util.Collection;

public interface CompanyService {
    Company create(Company company);

    Collection<Company> companyList(int limit);

    Company get(int id) throws AppException;

    Company update(Company company);

    Boolean delete(int id);

    void relateLocation(String companyName, String location);

}
