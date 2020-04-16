package edu.fzu.anop.util;

import com.github.pagehelper.PageHelper;
import edu.fzu.anop.resource.GroupResource;
import edu.fzu.anop.resource.PageParmResource;

public class PageSortHelper {
    public static void pageAndSort(PageParmResource pageParm, Class<?> sortClass) {
        String orderBy = pageParm.getOrderBy();
        if (!StringUtil.isNullOrWhiteSpace(orderBy)) {
            orderBy = pageParm.getOrderBy().trim();
            String[] strings = orderBy.split("\\s+");
            if (strings.length > 1) {
                if (PropertyMapperUtil.hasProperty(strings[0], GroupResource.class)) {
                    String property = PropertyMapperUtil.getUnderscoreFormat(strings[0]);
                    PageHelper.startPage(pageParm.getPageNum(), pageParm.getPageSize(), property + " " + strings[1]);
                    return;
                }
            }
        }
        PageHelper.startPage(pageParm.getPageNum(), pageParm.getPageSize());
    }
}
