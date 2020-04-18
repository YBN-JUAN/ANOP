package edu.fzu.anop.resource;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TodoListResource implements Serializable {

    private Integer id;

    private String title;

    private String content;

    private Date endDate;

    private Byte isCompleted;

    private Byte isImportant;

    private Byte isFavorite;
}
