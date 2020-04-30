package edu.fzu.anop.resource;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * 待办事项标记
 *
 * @author ZYF
 */
@Data
public class TodoFlagResource {
    @Range(min = 0,max = 2)
    private Byte flag = 0;
}
