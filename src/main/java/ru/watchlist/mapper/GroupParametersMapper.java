package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.report.GroupParameters;
import ru.watchlist.dto.report.GroupParametersDTO;
import ru.watchlist.dto.report.GroupParametersIdDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupParametersMapper {

    //GroupParametersDTO
    GroupParametersDTO toGroupParametersDTO(GroupParameters groupParameters);
    List<GroupParametersDTO> toGroupParametersDTOList(List<GroupParameters> groupsParameters);
    GroupParameters fromGroupParametersDTO(GroupParametersDTO groupParametersDTO);

    //GroupParametersIdDTO
    @Mapping(target = "typeReport", source = "typeReport.id")
    GroupParametersIdDTO toGroupParametersIdDTO(GroupParameters groupParameters);
    @Mapping(target = "typeReport.id", source = "typeReport")
    GroupParameters fromGroupParametersIdDTO(GroupParametersIdDTO groupParametersIdDTO);

    //GroupParameters
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void fillGroupParameters(GroupParameters source, @MappingTarget GroupParameters target);
    
}
