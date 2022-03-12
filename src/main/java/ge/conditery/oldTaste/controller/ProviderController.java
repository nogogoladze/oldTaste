package ge.conditery.oldTaste.controller;

import ge.conditery.oldTaste.model.Provider;
import ge.conditery.oldTaste.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/")
public class ProviderController {
    private final ProviderService providerService;

    @GetMapping("provider")
    public Collection<Provider> getAllProvider() {
        return providerService.providerList(10);
    }

    @PostMapping("provider/save")
    public Provider saveProvider(@RequestBody Provider provider) {
        return providerService.create(provider);
    }

    @PutMapping("provider/update")
    public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider) {
        Provider updateProvider = providerService.update(provider);
        return new ResponseEntity<>(updateProvider, HttpStatus.OK);
    }

    @PostMapping("provider/company/{providerId}/{companyName}")
    public void relateCar(@PathVariable Integer providerId,
                          @PathVariable String companyName) {
        providerService.relateCompany(providerId, companyName);
    }

    @DeleteMapping("provider/delete/{id}")
    public void deleteCar(@PathVariable Integer id) {
        try {
            providerService.delete(id);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
