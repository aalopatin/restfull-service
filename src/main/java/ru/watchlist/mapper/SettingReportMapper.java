package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.SettingReport;
import ru.watchlist.dto.settingreport.SettingReportFullDTO;
import ru.watchlist.dto.settingreport.SettingReportIdDTO;
import ru.watchlist.dto.settingreport.SettingReportListDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SettingReportMapper {

    //SettingReportIdDTO
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "companyTitle", source = "company.title")
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "groupTitle", source = "group.title")
    SettingReportIdDTO toSettingReportIdDTO(SettingReport settingReport);

    @Mapping(target = "company.id", source = "companyId")
    @Mapping(target = "group.id", source = "groupId")
    SettingReport fromSettingReportIdDTO(SettingReportIdDTO settingReportIdDTO);



    List<SettingReportIdDTO> toSettingReportIdDTOList(List<SettingReport> parameters);

    //SettingReportListDTO
    SettingReportListDTO toSettingReportListDTO(SettingReport settingReport);
    List<SettingReportListDTO> toSettingReportListDTOList(List<SettingReport> parameters);

    //SettingReportFullDTO
    SettingReportFullDTO toSettingReportFullDTO(SettingReport settingReport);
    List<SettingReportFullDTO> toSettingReportFullDTOList(List<SettingReport> settingsReports);

    //SettingReport
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void fillSettingReport(SettingReport source, @MappingTarget SettingReport target);
}
