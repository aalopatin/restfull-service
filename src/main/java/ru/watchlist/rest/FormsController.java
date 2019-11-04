package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.report.GroupParameters;
import ru.watchlist.domain.report.Parameter;
import ru.watchlist.domain.report.TypeReport;
import ru.watchlist.dto.Form;
import ru.watchlist.dto.report.ParameterDTO;
import ru.watchlist.mapper.GroupParametersMapper;
import ru.watchlist.mapper.ParameterMapper;
import ru.watchlist.mapper.TypeReportMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.GroupParametersService;
import ru.watchlist.service.ParameterService;
import ru.watchlist.service.TypeReportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/forms")
public class FormsController {

    @Autowired
    ParameterService parameterService;
    @Autowired
    ParameterMapper parameterMapper;

    @Autowired
    GroupParametersService groupParametersService;
    @Autowired
    GroupParametersMapper groupParametersMapper;

    @Autowired
    TypeReportService typeReportService;
    @Autowired
    TypeReportMapper typeReportMapper;

    @GetMapping("/lists/parameters")
    public ResponseEntity<Form> getListParameters() {

        Form form = new Form();

        List<Parameter> parameters = parameterService.getAll();
        form.setData(parameterMapper.toParameterIdDTOList(parameters));

        Map<String, Object> payloads = new HashMap<>();

        addPayloadsGroups(payloads);
        addPayloadsTypesReport(payloads);

        form.setPayloads(payloads);

        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    @GetMapping("/create/parameters")
    public ResponseEntity<Form> getCreateParameter() {

        Form form = new Form();

        Map<String, Object> payloads = new HashMap<>();

        addPayloadsGroups(payloads);
        addPayloadsTypesReport(payloads);

        form.setPayloads(payloads);

        return new ResponseEntity<>(form, HttpStatus.OK);

    }

    @GetMapping("/parameters/{id}")
    public ResponseEntity<Form> getParameter(@PathVariable Long id) throws EntityNotFoundException {

        Form form = new Form();

        Parameter parameter = parameterService.findById(id);
        form.setData(parameterMapper.toParameterIdDTO(parameter));

        Map<String, Object> payloads = new HashMap<>();
        addPayloadsGroups(payloads);
        addPayloadsTypesReport(payloads);
        form.setPayloads(payloads);

        return new ResponseEntity<>(form, HttpStatus.OK);

    }

//    @GetMapping("/forms/lists/reports/")
//    public ResponseEntity<Form> getListReport() {
//        Form form = new Form();
//
//        return new ResponseEntity<>(form, HttpStatus.OK);
//    }

    @GetMapping("/lists/reports/")
    public ResponseEntity<Form> getListReportCompany(@RequestParam String companyId) {
        Form form = new Form();

        return new ResponseEntity<>(form, HttpStatus.OK);
    }

    private void addPayloadsGroups(Map<String, Object> payloads) {
        List<GroupParameters> groups = groupParametersService.getAll();
        payloads.put("groups", groupParametersMapper.toGroupParametersIdDTOList(groups));
    }

    private void addPayloadsTypesReport(Map<String, Object> payloads) {
        List<TypeReport> typesReport = typeReportService.getAll();
        payloads.put("typesReport", typeReportMapper.toTypeReportDTOList(typesReport));
    }

}
