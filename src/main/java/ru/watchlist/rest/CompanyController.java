package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.watchlist.config.property.FileStorageProperties;
import ru.watchlist.domain.Company;
import ru.watchlist.dto.company.CompanyDTO;
import ru.watchlist.mapper.CompanyMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.CompanyService;
import ru.watchlist.service.FileStorageService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        Company company = companyMapper.fromDTO(companyDTO);
        companyService.createCompany(company);
        return new ResponseEntity<>(companyMapper.toDTO(company), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Long id) throws EntityNotFoundException {
        Company company = companyService.findById(id);

        return new ResponseEntity<>(companyMapper.toDTO(company), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAllCompany() {
        List<Company> companies = companyService.getAll();
        return new ResponseEntity<>(companyMapper.toDTOList(companies), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> saveCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) throws EntityNotFoundException {
        Company company = companyService.saveCompany(id, companyDTO);
        return new ResponseEntity<>(companyMapper.toDTO(company), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable Long id) throws EntityNotFoundException {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
