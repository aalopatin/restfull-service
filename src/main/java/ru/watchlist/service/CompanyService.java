package ru.watchlist.service;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.watchlist.config.property.FileStorageProperties;
import ru.watchlist.domain.Company;
import ru.watchlist.dto.company.CompanyDTO;
import ru.watchlist.mapper.CompanyMapper;
import ru.watchlist.repository.CompanyRepository;
import ru.watchlist.rest.exception.EntityNotFoundException;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyMapper companyMapper;

    //Repository

    @Named("findCompanyById")
    public Company findById(Long id) throws EntityNotFoundException {

        if(id != null) {
            Company company = companyRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(Company.class, "id", id.toString()));
            return company;
        } else {
            return null;
        }

    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company saveCompany(Long id, CompanyDTO companyDTO) throws EntityNotFoundException {
        Company companyFromDB = findById(id);
        companyMapper.fill(companyDTO, companyFromDB);
        return companyRepository.save(companyFromDB);
    }

    public void deleteCompany(Long id) throws EntityNotFoundException {
        try {
            companyRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException(Company.class, "id", id.toString());
        }

    }

    public String saveLogoFileName(Company company, String logoFileName) {

        company.setLogo(logoFileName);
        companyRepository.save(company);
        return logoFileName;
    }

    public String getLogoFileName(Long id) throws EntityNotFoundException {
        Company company = findById(id);
        if (company != null) {
            return company.getLogo();
        }
        return "";
    }

}
