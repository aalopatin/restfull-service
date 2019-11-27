package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.GroupParameters;
import ru.watchlist.dto.GroupParametersDTO;
import ru.watchlist.dto.GroupParametersIdDTO;
import ru.watchlist.mapper.GroupParametersMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.GroupParametersService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/groupsparameters")
public class GroupParametersController {

    @Autowired
    private GroupParametersService groupParametersService;

    @Autowired
    private GroupParametersMapper groupParametersMapper;

    @PostMapping
    public ResponseEntity<GroupParametersIdDTO> createGroupParameters(@RequestBody GroupParametersIdDTO groupParametersIdDTO) {
        GroupParameters groupParameters = groupParametersMapper.fromGroupParametersIdDTO(groupParametersIdDTO);
        groupParameters = groupParametersService.createGroupParameters(groupParameters);
        return new ResponseEntity<>(groupParametersMapper.toGroupParametersIdDTO(groupParameters), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupParametersIdDTO> getGroupParameters(@PathVariable Long id) throws EntityNotFoundException {
        GroupParameters groupParameters = groupParametersService.findById(id);
        return new ResponseEntity<>(groupParametersMapper.toGroupParametersIdDTO(groupParameters), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GroupParametersDTO>> getAllGroupParameters() {
        List<GroupParameters> groupsParameters = groupParametersService.getAll();
        return new ResponseEntity<>(groupParametersMapper.toGroupParametersDTOList(groupsParameters), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupParametersIdDTO> saveGroupParameters(@PathVariable Long id, @RequestBody GroupParametersIdDTO groupParametersIdDTO) throws EntityNotFoundException {
        GroupParameters groupParameters = groupParametersMapper.fromGroupParametersIdDTO(groupParametersIdDTO);
        groupParameters = groupParametersService.saveGroupParameters(id, groupParameters);
        return new ResponseEntity<>(groupParametersMapper.toGroupParametersIdDTO(groupParameters), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGroupParameters(@PathVariable Long id) throws EntityNotFoundException {
        groupParametersService.deleteGroupParameters(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
