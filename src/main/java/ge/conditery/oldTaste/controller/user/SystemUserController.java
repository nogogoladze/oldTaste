package ge.conditery.oldTaste.controller.user;

import ge.conditery.oldTaste.model.system.SystemUser;
import ge.conditery.oldTaste.service.SystemUserService;
import ge.conditery.oldTaste.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1")
public class SystemUserController {
    private final SystemUserService systemUserService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/registration")
    public SystemUser saveUser(@RequestBody SystemUser systemUser) throws Exception {
        try {
            boolean emailExist = systemUserService.findByEmail(systemUser.getEmail());
            if (emailExist) {
                throw new Exception("email exist");
            }

            systemUser.setPassword(bCryptPasswordEncoder.encode(systemUser.getPassword()));
            systemUser.setActive(false);
            String randomNumber = Utils.getRandomNumber();

            systemUser.setRandomNumber(randomNumber);

        } catch (Exception exception) {
            exception.getMessage();
        }

        return systemUserService.saveSystemUser(systemUser);
    }
}
