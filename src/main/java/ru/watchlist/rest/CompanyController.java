package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.Company;
import ru.watchlist.dto.CompanyAbstract;
import ru.watchlist.dto.CompanyDTO;
import ru.watchlist.mapper.CompanyMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        Company company = companyMapper.fromCompanyDTO(companyDTO);
        company = companyService.createCompany(company);
        return new ResponseEntity<>(companyMapper.toCompanyDTO(company), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Long id) throws EntityNotFoundException {
        Company company = companyService.findById(id);
        return new ResponseEntity<>(companyMapper.toCompanyDTO(company), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAllCompany() {
        List<Company> companies = companyService.getAll();
        return new ResponseEntity<>(companyMapper.toCompanyDTOList(companies), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> saveCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) throws EntityNotFoundException {
        Company company = companyMapper.fromCompanyDTO(companyDTO);
        company = companyService.saveCompany(id, company);
        return new ResponseEntity<>(companyMapper.toCompanyDTO(company), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable Long id) throws EntityNotFoundException {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
