package ge.conditery.oldTaste.service;

import ge.conditery.oldTaste.model.system.SystemUser;

public interface SystemUserService {
    SystemUser saveSystemUser(SystemUser systemUser);

    boolean findByEmail(String email);
}
