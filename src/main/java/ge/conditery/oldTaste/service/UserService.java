package ge.conditery.oldTaste.service;

import ge.conditery.oldTaste.model.system.User;

public interface UserService {
    User saveSystemUser(User systemUser) throws Exception;
}
