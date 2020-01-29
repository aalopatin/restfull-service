package ru.watchlist.service;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.watchlist.domain.Company;
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

    public Company saveCompany(Long id, Company company) throws EntityNotFoundException {
        Company companyFromDB = findById(id);
        companyMapper.fill(company, companyFromDB);
        return companyRepository.save(companyFromDB);
    }

    public void deleteCompany(Long id) throws EntityNotFoundException {
        try {
            companyRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException(Company.class, "id", id.toString());
        }

    }

}
