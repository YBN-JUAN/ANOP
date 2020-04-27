package edu.fzu.anop.resource;

import lombok.Data;

/**
 * 待办事项分类列表资源
 *
 * @author ZYF
 */
@Data
public class CategoryListResource {

    private Integer id;

    private String typeName;

    private Integer todoNum;
}
