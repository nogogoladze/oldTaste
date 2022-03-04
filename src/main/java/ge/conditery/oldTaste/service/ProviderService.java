package ge.conditery.oldTaste.service;

import ge.conditery.oldTaste.model.Provider;

import java.util.Collection;

public interface ProviderService {
    Provider create(Provider provider);

    Collection<Provider> providerList(int limit);

    Provider get(int id);

    Provider update(Provider provider);

    Boolean delete(int id);

    void relateCompany(Integer providerId, String companyName);
}
