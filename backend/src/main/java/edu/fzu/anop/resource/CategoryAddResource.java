package edu.fzu.anop.resource;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author ZYF
 */
@Data
public class CategoryAddResource {

    @NotNull
    @Length(min = 1, max = 15)
    private String typeName;

}
