package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.report.GroupParameters;
import ru.watchlist.dto.report.GroupParametersDTO;
import ru.watchlist.mapper.GroupParametersMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.GroupParametersService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/groupparameters")
public class GroupParametersController {

    @Autowired
    private GroupParametersService groupParametersService;

    @Autowired
    private GroupParametersMapper groupParametersMapper;

    @PostMapping
    public ResponseEntity<GroupParametersDTO> createGroupParameters(@RequestBody GroupParametersDTO groupParametersDTO) {
        GroupParameters groupParameters = groupParametersMapper.fromGroupParametersDTO(groupParametersDTO);
        groupParameters = groupParametersService.createGroupParameters(groupParameters);
        return new ResponseEntity<>(groupParametersMapper.toGroupParametersDTO(groupParameters), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupParametersDTO> getGroupParameters(@PathVariable Long id) throws EntityNotFoundException {
        GroupParameters groupParameters = groupParametersService.findById(id);
        return new ResponseEntity<>(groupParametersMapper.toGroupParametersDTO(groupParameters), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GroupParametersDTO>> getAllGroupParameters() {
        List<GroupParameters> groupsParameters = groupParametersService.getAll();
        return new ResponseEntity<>(groupParametersMapper.toGroupParametersDTOList(groupsParameters), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupParametersDTO> saveGroupParameters(@PathVariable Long id, @RequestBody GroupParametersDTO groupParametersDTO) throws EntityNotFoundException {
        GroupParameters groupParameters = groupParametersMapper.fromGroupParametersDTO(groupParametersDTO);
        groupParameters = groupParametersService.saveGroupParameters(id, groupParameters);
        return new ResponseEntity<>(groupParametersMapper.toGroupParametersDTO(groupParameters), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGroupParameters(@PathVariable Long id) throws EntityNotFoundException {
        groupParametersService.deleteGroupParameters(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
