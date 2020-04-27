package edu.fzu.anop.resource;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 待办事项列表资源
 *
 * @author ZYF
 */
@Data
public class TodoResource implements Serializable {

    private Integer id;

    private String title;

    private String content;

    private Date endDate;

    private Byte isCompleted;

    private Byte isImportant;

    private Byte isFavorite;
}
