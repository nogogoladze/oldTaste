package ge.conditery.oldTaste.controller;

import ge.conditery.oldTaste.model.Company;
import ge.conditery.oldTaste.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("company")
    public ResponseEntity<Collection<Company>> getAllCompany() {
        Collection<Company> companyList = companyService.companyList(10);
        return new ResponseEntity<>(companyList, HttpStatus.OK);
    }

    @PostMapping("company/save")
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        Company newCompany = companyService.create(company);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @PutMapping("company/update")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        Company updateCompany = companyService.update(company);
        return new ResponseEntity<>(updateCompany, HttpStatus.OK);
    }

    @PostMapping("company/location/{companyName}/{address}")
    public void relateCar(@PathVariable String companyName,
                          @PathVariable String address) {
        companyService.relateLocation(companyName, address);
    }

    @DeleteMapping("company/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Integer id) {
        companyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
