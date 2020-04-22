package edu.fzu.anop.resource;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class TodoAddResource implements Serializable {

    @NotNull
    @Length(min = 1, max = 15)
    private String title;

    @NotNull
    @Length(max = 200)
    private String content;

    @Future
    private Date beginDate;

    @Future
    private Date endDate;

    @Future
    private Date remindDate;

    private Integer categoryId;

    @NotNull
    @Range(min = 0, max = 1)
    private Byte isImportant;

    @NotNull
    @Range(min = 0, max = 1)
    private Byte isFavorite;


    // TODO
}
