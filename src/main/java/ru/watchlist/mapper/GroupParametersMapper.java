package ru.watchlist.mapper;

import org.mapstruct.*;
import ru.watchlist.domain.GroupParameters;
import ru.watchlist.dto.GroupParametersDTO;
import ru.watchlist.dto.GroupParametersIdDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupParametersMapper {

    //GroupParametersDTO
    GroupParametersDTO toGroupParametersDTO(GroupParameters groupParameters);
    List<GroupParametersDTO> toGroupParametersDTOList(List<GroupParameters> groupsParameters);
    GroupParameters fromGroupParametersDTO(GroupParametersDTO groupParametersDTO);

    //GroupParametersIdDTO
    @Mapping(target = "typeReportId", source = "typeReport.id")
    GroupParametersIdDTO toGroupParametersIdDTO(GroupParameters groupParameters);
    List<GroupParametersIdDTO> toGroupParametersIdDTOList(List<GroupParameters> groups);
    @Mapping(target = "typeReport.id", source = "typeReportId")
    GroupParameters fromGroupParametersIdDTO(GroupParametersIdDTO groupParametersIdDTO);

    //GroupParameters
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "id", ignore = true)
    void fillGroupParameters(GroupParameters source, @MappingTarget GroupParameters target);


}
