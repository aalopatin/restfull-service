package ru.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.watchlist.domain.SettingReport;

public interface SettingReportRepository extends JpaRepository<SettingReport, Long>, JpaSpecificationExecutor<SettingReport> {

}
