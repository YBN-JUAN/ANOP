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
            if (strings.length > 0) {
                if (PropertyMapperUtil.hasProperty(strings[0], sortClass)) {
                    String property = PropertyMapperUtil.getUnderscoreFormat(strings[0]);
                    if (strings.length > 1) {
                        PageHelper.startPage(pageParm.getPageNum(), pageParm.getPageSize(), property + " " + strings[1]);
                    } else {
                        PageHelper.startPage(pageParm.getPageNum(), pageParm.getPageSize(), property);
                    }
                    return;
                }
            }
        }
        PageHelper.startPage(pageParm.getPageNum(), pageParm.getPageSize());
    }
}
