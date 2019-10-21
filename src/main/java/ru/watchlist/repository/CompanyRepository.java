package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.watchlist.domain.company.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
