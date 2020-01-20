package ru.watchlist.dto.settingreport;

import lombok.Data;
import ru.watchlist.dto.CompanyDTO;
import ru.watchlist.dto.GroupParametersDTO;
import ru.watchlist.dto.RowSettingReportDTO;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
public class SettingReportFullDTO extends SettingReportAbstract {

    private CompanyDTO company;
    private GroupParametersDTO group;
    private List<RowSettingReportDTO> rows;

}
