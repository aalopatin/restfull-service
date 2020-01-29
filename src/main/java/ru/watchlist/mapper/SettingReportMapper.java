package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.SettingReport;
import ru.watchlist.dto.settingreport.SettingReportDTO;
import ru.watchlist.dto.settingreport.SettingReportIdDTO;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.CompanyService;
import ru.watchlist.service.GroupParameterService;
import ru.watchlist.service.TypeReportService;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CompanyService.class, GroupParameterService.class, RowSettingReportMapper.class, TypeReportService.class})
public interface SettingReportMapper {

    //SettingReportDTO
    SettingReportDTO toDTO(SettingReport settingReport);
    List<SettingReportDTO> toDTOList(List<SettingReport> parameters);

    //SettingReportIdDTO
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "typeReportId", source = "typeReport.id")
    SettingReportIdDTO toIdDTO(SettingReport settingReport);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", source = "companyId", qualifiedByName = { "findCompanyById" })
    @Mapping(target = "group", source = "groupId", qualifiedByName = { "findGroupById" })
    @Mapping(target = "typeReport", source = "typeReportId", qualifiedByName = { "findTypeReportById" })
    SettingReport fromIdDTO(SettingReportIdDTO settingReportIdDTO) throws EntityNotFoundException;
    List<SettingReportIdDTO> toIdDTOList(List<SettingReport> parameters);

    //SettingReport
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fill(SettingReport source, @MappingTarget SettingReport target);
}
