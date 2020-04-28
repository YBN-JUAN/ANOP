package edu.fzu.anop.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 待办事项类型标记
 *
 * @author ZYF
 */
@Data
@ApiModel(value = "待办事项类型")
public class TodoFlagResource {
    @Min(0)
    @Max(2)
    @ApiModelProperty(value = "待办事项类型标记，0:所有 1:重要 2:收藏", name = "flag", example = "0")
    private Byte flag = 0;
}
