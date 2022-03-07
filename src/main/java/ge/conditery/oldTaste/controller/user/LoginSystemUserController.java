package ge.conditery.oldTaste.controller.user;

import ge.conditery.oldTaste.model.system.SystemUser;
import ge.conditery.oldTaste.service.implementation.SysUserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1")
public class LoginSystemUserController {
    private final SysUserServiceImpl sysUserServiceImpl;

    @PostMapping("/login")
    public SystemUser loadUser(@RequestBody SystemUser systemUser) {

        return (SystemUser) sysUserServiceImpl.loadUserByUsername(systemUser.getUserName());

    }

}
