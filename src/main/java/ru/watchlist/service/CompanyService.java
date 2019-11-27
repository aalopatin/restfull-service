package ru.watchlist.service;

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

    public Company findById(Long id) throws EntityNotFoundException {

        Company company = companyRepository.findById(id).orElse(null);

        if (company==null) {
            throw new EntityNotFoundException(Company.class, "id", id.toString());
        }

        return company;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company createCompany(Company company) {
        Company createdCompany = companyRepository.save(company);
        return createdCompany;
    }

    public Company saveCompany(Long id, Company company) throws EntityNotFoundException {
        Company companyFromDB = findById(id);
        companyMapper.fillCompany(company, companyFromDB);
        companyRepository.save(companyFromDB);
        return companyFromDB;
    }

    public void deleteCompany(Long id) throws EntityNotFoundException {
        try {
            companyRepository.deleteById(id);
        } catch (IllegalArgumentException ex) {
            throw new EntityNotFoundException(Company.class, "id", id.toString());
        }

    }

}
