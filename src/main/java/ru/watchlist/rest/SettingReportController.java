package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.SettingReport;
import ru.watchlist.dto.Variant;
import ru.watchlist.dto.settingreport.SettingReportAbstract;
import ru.watchlist.dto.settingreport.SettingReportIdDTO;
import ru.watchlist.mapper.SettingReportMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.SettingReportService;

import java.util.List;

@RestController
@RequestMapping("/api/settingsreports")
public class SettingReportController {

    @Autowired
    SettingReportService settingReportService;

    @Autowired
    private SettingReportMapper settingReportMapper;

    @PostMapping
    public ResponseEntity<SettingReportIdDTO> create(@RequestBody SettingReportIdDTO settingReportIdDTO) throws EntityNotFoundException {
        SettingReport settingReport = settingReportMapper.fromIdDTO(settingReportIdDTO);
        settingReportService.create(settingReport);
        return new ResponseEntity<>(settingReportMapper.toIdDTO(settingReport), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<? extends SettingReportAbstract>> findAll(@RequestParam(value = "variant", required = false) Variant variant,
                                                                         @RequestParam(value = "rows", required = false) boolean rows,
                                                                         @RequestParam(value = "search", required = false) String search) {

        List<SettingReport> settingsReports = settingReportService.findAll(search);
        return new ResponseEntity<>(variantDTOList(variant, rows, settingsReports), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends SettingReportAbstract> get(@PathVariable Long id,
                                                               @RequestParam(value = "variant", required = false) Variant variant,
                                                               @RequestParam(value = "rows", required = false) boolean rows) throws EntityNotFoundException {

        SettingReport settingReport = settingReportService.findById(id);
        return new ResponseEntity<>(variantDTO(variant, rows, settingReport), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SettingReportIdDTO> save(@PathVariable Long id, @RequestBody SettingReportIdDTO settingReportDTO) throws EntityNotFoundException {
        SettingReport settingReport = settingReportService.save(id, settingReportDTO);
        return new ResponseEntity<>(settingReportMapper.toIdDTO(settingReport), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) throws EntityNotFoundException {
        settingReportService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private SettingReportAbstract variantDTO(Variant variant, boolean rows, SettingReport settingReport) {
        if (variant == null) variant = Variant.DEFAULT;
        switch (variant) {
            case ID:
                if (rows) {
                    return settingReportMapper.toIdDTO(settingReport);
                } else {
                    return settingReportMapper.toIdNoRowsDTO(settingReport);
                }
            default:
                return settingReportMapper.toDTO(settingReport);
        }
    }

    private List<? extends SettingReportAbstract> variantDTOList(Variant variant, boolean rows, List<SettingReport> settingsReports) {
        if (variant == null) variant = Variant.DEFAULT;
        switch (variant) {
            case ID:
                if (rows) {
                    return settingReportMapper.toIdDTOList(settingsReports);
                } else {
                    return settingReportMapper.toIdNoRowsDTOList(settingsReports);
                }

            default:
                return settingReportMapper.toDTOList(settingsReports);
        }
    }


}
