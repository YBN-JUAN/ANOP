package edu.fzu.anop.resource;

import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PageParmResource {
    @Min(1)
    @Max(20)
    private int pageSize = 5;
    @Min(1)
    private int pageNum = 1;

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
}
