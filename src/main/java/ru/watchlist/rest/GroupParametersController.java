package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.GroupParameter;
import ru.watchlist.dto.Variant;
import ru.watchlist.dto.groupparameter.GroupParameterAbstract;
import ru.watchlist.dto.groupparameter.GroupParameterIdDTO;
import ru.watchlist.mapper.GroupParameterMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.GroupParameterService;

import java.util.List;

@RestController
@RequestMapping("/api/groupsparameters")
public class GroupParametersController {

    @Autowired
    private GroupParameterService groupParameterService;

    @Autowired
    private GroupParameterMapper groupParameterMapper;

    @PostMapping
    public ResponseEntity<GroupParameterIdDTO> createGroupParameters(@RequestBody GroupParameterIdDTO groupParameterIdDTO) throws EntityNotFoundException {
        GroupParameter groupParameter = groupParameterMapper.fromIdDTO(groupParameterIdDTO);
        groupParameterService.createGroupParameter(groupParameter);
        return new ResponseEntity<>(groupParameterMapper.toIdDTO(groupParameter), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends GroupParameterAbstract> getGroupParameter(@PathVariable Long id,
                                                                              @RequestParam(value = "variant", required = false) Variant variant) throws EntityNotFoundException {
        GroupParameter groupParameter = groupParameterService.findById(id);
        return new ResponseEntity<>(variantDTO(variant, groupParameter), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<? extends GroupParameterAbstract>> findAll(@RequestParam(value = "variant", required = false) Variant variant,
                                                                          @RequestParam(value = "search", required = false) String search) {

        List<GroupParameter> groupParameterList = groupParameterService.findAll();
        return new ResponseEntity<>(variantDTOList(variant, groupParameterList), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupParameterIdDTO> saveGroupParameters(@PathVariable Long id, @RequestBody GroupParameterIdDTO groupParameterIdDTO) throws EntityNotFoundException {
        GroupParameter groupParameter = groupParameterMapper.fromIdDTO(groupParameterIdDTO);
        groupParameter = groupParameterService.saveGroupParameter(id, groupParameter);
        return new ResponseEntity<>(groupParameterMapper.toIdDTO(groupParameter), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGroupParameters(@PathVariable Long id) throws EntityNotFoundException {
        groupParameterService.deleteGroupParameter(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private GroupParameterAbstract variantDTO(Variant variant, GroupParameter entity) {
        switch (variant) {
            case ID:
                return groupParameterMapper.toIdDTO(entity);
            default:
                return groupParameterMapper.toDTO(entity);
        }
    }

    private List<? extends GroupParameterAbstract> variantDTOList(Variant variant, List<GroupParameter> entityList) {
        switch (variant) {
            case ID:
                return groupParameterMapper.toIdDTOList(entityList);
            default:
                return groupParameterMapper.toDTOList(entityList);
        }
    }

}
