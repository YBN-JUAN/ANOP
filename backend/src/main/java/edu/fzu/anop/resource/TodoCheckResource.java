package edu.fzu.anop.resource;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 标记待办事项为完成时的请求体
 */
@Data
public class TodoCheckResource implements Serializable {

    @NotNull
    private Integer id;

    @NotNull
    @Range(min = 0, max = 1)
    private Byte isCompleted;
}
