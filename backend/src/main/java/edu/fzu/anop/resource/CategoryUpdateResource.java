package edu.fzu.anop.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author ZYF
 */
@Data
@ApiModel(value = "更新待办事项分类请求体")
public class CategoryUpdateResource {

    @NotNull
    @Length(min = 1, max = 20)
    @ApiModelProperty(value = "分类名称", name = "typeName", example = "我的分类")
    private String typeName;
}
