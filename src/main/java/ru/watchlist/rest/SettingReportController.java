package ru.watchlist.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.watchlist.domain.SettingReport;
import ru.watchlist.dto.settingreport.SettingReportAbstract;
import ru.watchlist.dto.settingreport.SettingReportIdDTO;
import ru.watchlist.mapper.SettingReportMapper;
import ru.watchlist.rest.exception.EntityNotFoundException;
import ru.watchlist.service.SettingReportService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/settingsreports")
public class SettingReportController {

    @Autowired
    SettingReportService settingReportService;

    @Autowired
    private SettingReportMapper settingReportMapper;

    @PostMapping
    public ResponseEntity<SettingReportIdDTO> createSettingReport(@RequestBody SettingReportIdDTO settingReportIdDTO) {
        SettingReport settingReport = settingReportMapper.fromSettingReportIdDTO(settingReportIdDTO);
        settingReport = settingReportService.createSettingReport(settingReport);
        return new ResponseEntity<>(settingReportMapper.toSettingReportIdDTO(settingReport), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<? extends SettingReportAbstract>> findAllSettingReport(@RequestParam(value = "variant", required = false) String variant,
                                                                                      @RequestParam(value = "search", required = false) String search) {

        List<SettingReport> settingsReports = settingReportService.findAll(search);
        return new ResponseEntity<>(variantSettingsReportsDTO(variant, settingsReports), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends SettingReportAbstract> getCompany(@PathVariable Long id,
                                                                      @RequestParam(value = "variant", required = false) String variant) throws EntityNotFoundException {

        SettingReport settingReport = settingReportService.findById(id);
        return new ResponseEntity<>(variantSettingReportDTO(variant, settingReport), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SettingReportIdDTO> saveSettingReport(@PathVariable Long id, @RequestBody SettingReportIdDTO settingReportDTO) throws EntityNotFoundException {
        SettingReport settingReport = settingReportMapper.fromSettingReportIdDTO(settingReportDTO);
        settingReport = settingReportService.saveSettingReport(id, settingReport);
        return new ResponseEntity<>(settingReportMapper.toSettingReportIdDTO(settingReport), HttpStatus.OK);
    }

    private SettingReportAbstract variantSettingReportDTO(String variant, SettingReport settingReport) {
        if (variant == null) variant = "";
        switch (variant) {
            case ("full"):
                return settingReportMapper.toSettingReportFullDTO(settingReport);
            case ("id"):
                return settingReportMapper.toSettingReportIdDTO(settingReport);
            default:
                return settingReportMapper.toSettingReportListDTO(settingReport);
        }
    }

    private List<? extends SettingReportAbstract> variantSettingsReportsDTO(String variant, List<SettingReport> settingsReports) {
        if (variant == null) variant = "";
        switch (variant) {
            case ("full"):
                return settingReportMapper.toSettingReportFullDTOList(settingsReports);
            case ("id"):
                return settingReportMapper.toSettingReportIdDTOList(settingsReports);
            default:
                return settingReportMapper.toSettingReportListDTOList(settingsReports);
        }
    }


}
