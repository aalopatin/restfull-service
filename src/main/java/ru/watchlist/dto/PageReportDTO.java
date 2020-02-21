package ru.watchlist.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.watchlist.domain.Report;
import ru.watchlist.dto.report.ReportAbstract;
import ru.watchlist.dto.report.ReportIdDTO;

import java.util.List;

@Data
public class PageReportDTO {

    private List<? extends ReportAbstract> reports;
    private Long total;
    private int pages;

}
