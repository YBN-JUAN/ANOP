package edu.fzu.anop.resource;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class PageParmResource {
    @Min(1)
    @Max(20)
    private int pageSize = 5;
    @Min(1)
    private int pageNum = 1;

    @Pattern(regexp = "\\s*\\w+\\s+([Aa][Ss][Cc]|[Dd][Ee][Ss][Cc])?\\s*")
    @Length(max = 20)
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
