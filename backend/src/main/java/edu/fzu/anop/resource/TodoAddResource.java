package edu.fzu.anop.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 待办事项添加资源
 *
 * @author ZYF
 */
@Data
@ApiModel(value = "新建待办事项请求体")
public class TodoAddResource implements Serializable {

    @NotNull
    @Length(min = 1, max = 15)
    @ApiModelProperty(value = "待办名称", name = "title", example = "我的待办")
    private String title;

    @NotNull
    @Length(max = 200)
    @ApiModelProperty(value = "待办内容", name = "content", example = "阿道夫就安分的链接克隆。。。。")
    private String content;

    @ApiModelProperty(value = "开始时间", name = "beginDate", example = "2020-04-01 19:00")
    private Date beginDate;

    @ApiModelProperty(value = "结束时间（要大于当前时间）", name = "beginDate", example = "2020-04-01 19:00")
    private Date endDate;

    @ApiModelProperty(value = "提醒时间（要大于当前时间）", name = "remindDate", example = "2020-04-01 19:00")
    private Date remindDate;

    @ApiModelProperty(value = "所属分类id", name = "categoryId", example = "1")
    private Integer categoryId;

    @NotNull
    @Range(min = 0, max = 1)
    @ApiModelProperty(value = "重要标记", name = "isImportant", example = "1")
    private Byte isImportant;

    @NotNull
    @Range(min = 0, max = 1)
    @ApiModelProperty(value = "收藏标记", name = "isFavorite", example = "1")
    private Byte isFavorite;

}
