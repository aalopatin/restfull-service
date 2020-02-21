package ru.watchlist.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class DTOAbstract {

    private Date createdOn;
    private Date modifiedOn;

}
