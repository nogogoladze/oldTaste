package ge.conditery.oldTaste.controller.user;

import ge.conditery.oldTaste.model.system.SystemUser;
import ge.conditery.oldTaste.service.SystemUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/")
public class SystemUserController {
    private final SystemUserService systemUserService;

    @PostMapping("registration")
    public SystemUser saveUser(@RequestBody SystemUser systemUser) throws Exception {
        return systemUserService.saveSystemUser(systemUser);
    }
}
