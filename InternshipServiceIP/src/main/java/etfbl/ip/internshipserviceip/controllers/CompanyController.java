package etfbl.ip.internshipserviceip.controllers;

import etfbl.ip.internshipserviceip.entities.Company;
import etfbl.ip.internshipserviceip.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company newCompany = companyService.addCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCompany);
    }

    @PutMapping
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        Company updated = companyService.updateCompany(company);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompanyById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}/toggle_activation")
    public ResponseEntity<Company> toggleActivation(@PathVariable Long id) {
        companyService.toggleActivation(id);
        return ResponseEntity.ok().build();
    }
}
