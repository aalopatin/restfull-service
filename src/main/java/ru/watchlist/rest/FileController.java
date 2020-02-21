package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.watchlist.domain.Company;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.rest.exception.FileStorageException;
import ru.watchlist.service.CompanyService;
import ru.watchlist.service.FileStorageService;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("companies/{id}/logo")
    public ResponseEntity<String> uploadLogo(@PathVariable Long id, @RequestParam("logo") MultipartFile logo) throws EntityNotFoundException {

        Company company = companyService.findById(id);
        String logoFileName = company.getLogo();
        if (logoFileName != null) {
            fileStorageService.deleteLogo(logoFileName);
        }
        logoFileName = fileStorageService.uploadLogo(id, logo);
        companyService.saveLogoFileName(company, logoFileName);
        return logoUri(logoFileName);

    }

    @GetMapping("/companies/{id}/logo")
    public ResponseEntity<String> getLogoURI(@PathVariable Long id)throws EntityNotFoundException {

        String logoFileName = companyService.getLogoFileName(id);
        return logoUri(logoFileName);

    }

    public ResponseEntity<String> logoUri(String logoFileName) {
        String logoURI =  fileStorageService.getLogoURI(logoFileName);
        return new ResponseEntity<>(logoURI, HttpStatus.OK);
    }

}
