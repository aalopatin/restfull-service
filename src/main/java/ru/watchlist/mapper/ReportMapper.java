package ru.watchlist.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.watchlist.domain.RowReport;
import ru.watchlist.domain.Report;
import ru.watchlist.dto.report.ReportIdNoRowsDTO;
import ru.watchlist.dto.report.RowReportIdDTO;
import ru.watchlist.dto.report.ReportIdDTO;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.CompanyService;
import ru.watchlist.service.PeriodService;
import ru.watchlist.service.TypeReportService;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompanyService.class, PeriodService.class, TypeReportService.class, RowReportMapper.class})
public interface ReportMapper {

    //ReportIdDTO -----------------------------------------------------
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target="periodId", source = "period.id")
    @Mapping(target="typeReportId", source = "typeReport.id")
    ReportIdDTO toIdDTO(Report report);
    List<ReportIdDTO> toIdDTOList(List<Report> reportList);

//    @Mapping(target = "id", ignore = true)
    @Mapping(target="company", source = "companyId", qualifiedByName = { "findCompanyById" })
    @Mapping(target="period", source = "periodId")
    @Mapping(target="typeReport", source = "typeReportId", qualifiedByName = { "findTypeReportById" })
    Report fromIdDTO(ReportIdDTO reportIdDTO) throws EntityNotFoundException;
    List<Report> fromIdDTOList(List<ReportIdDTO> reportIdDTOList) throws EntityNotFoundException;

    //SettingReportIdNORowsDTO
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "periodId", source = "period.id")
    @Mapping(target = "typeReportId", source = "typeReport.id")
    ReportIdNoRowsDTO toIdNoRowsDTO(Report report);
    List<ReportIdNoRowsDTO> toIdNoRowsDTOList(List<Report> reportList);

}
