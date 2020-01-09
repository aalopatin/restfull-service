package ru.watchlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.watchlist.domain.ParameterValue;
import ru.watchlist.domain.Report;
import ru.watchlist.dto.ParameterValueIdDTO;
import ru.watchlist.dto.ReportIdDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {


    //ReportIdDTO -----------------------------------------------------

    @Mapping(target="company.id", source = "companyId")
    @Mapping(target="period.id", source = "periodId")
    @Mapping(target="typeReport.id", source = "typeReportId")
    Report fromReportIdDTO(ReportIdDTO reportIdDTO);

    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "companyTitle", source = "company.title")
    @Mapping(target="periodId", source = "period.id")
    @Mapping(target="typeReportId", source = "typeReport.id")
    @Mapping(target="typeReportTitle", source = "typeReport.title")
    ReportIdDTO toReportIdDTO(Report report);

    List<Report> fromReportIdDTOList(List<ReportIdDTO> reportIdDTOList);
    List<ReportIdDTO> toReportIdDTOList(List<Report> reportList);

    //ParameterValueIdDTO ---------------------------------------------

    @Mapping(target = "parameterId", source = "parameter.id")
    @Mapping(target = "parameterTitle", source = "parameter.title")
    ParameterValueIdDTO toParameterValueIdDTO(ParameterValue parameterValue);

    @Mapping(target = "parameter.id", source = "parameterId")
    ParameterValue fromParameterValueIdDTO(ParameterValueIdDTO parameterValueIdDTO);

    List<ParameterValueIdDTO> toParameterIdDTOList(List<ParameterValue> parametersValue);
    List<ParameterValue> fromParameterValueIdDTOList(List<ParameterValueIdDTO> parameterValueIdDTOS);


}
