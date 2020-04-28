package edu.fzu.anop.resource;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 待办事项时间参数启用标记
 *
 * @author ZYF
 */
@ApiModel("时间参数启用标记")
@Data
public class DateFlagParamResource {

    @Min(0)
    @Max(1)
    @ApiModelProperty("是否启用开始时间")
    private Byte beginDateFlag;

    @Min(0)
    @Max(1)
    @ApiModelProperty("是否启用结束时间")
    private Byte endDateFlag;

    @Min(0)
    @Max(1)
    @ApiModelProperty("是否启用截止时间")
    private Byte remindDateFlag;
}
