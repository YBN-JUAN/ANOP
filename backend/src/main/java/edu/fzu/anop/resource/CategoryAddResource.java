package edu.fzu.anop.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 待办事项分类添加资源
 *
 * @author ZYF
 */
@Data
@ApiModel(value = "新建待办事项分类请求体")
public class CategoryAddResource {

    @NotNull
    @Length(min = 1, max = 15)
    @ApiModelProperty(value = "分类名称", name = "typeName", example = "我的分类")
    private String typeName;

}
