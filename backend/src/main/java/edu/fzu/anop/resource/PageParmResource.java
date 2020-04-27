package edu.fzu.anop.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 分页参数资源
 *
 * @author Xue_Feng
 */
@ApiModel("分页参数")
public class PageParmResource implements Serializable {
    @Min(1)
    @Max(20)
    @ApiModelProperty("页数据条目数，默认5")
    private int pageSize = 5;
    @Min(1)
    @ApiModelProperty("所在页号，默认1")
    private int pageNum = 1;

    @Pattern(regexp = "\\s*\\w+\\s*(\\s[Aa][Ss][Cc]|\\s[Dd][Ee][Ss][Cc])?\\s*")
    @Length(max = 20)
    @ApiModelProperty(value = "排序字段", example = "id desc")
    private String orderBy;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
