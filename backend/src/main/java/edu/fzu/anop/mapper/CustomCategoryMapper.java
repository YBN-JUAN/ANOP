package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.CategoryListResource;

import java.util.List;

/**
 * 自定义待办事项分类Mapper
 *
 * @author ZYF
 */
public interface CustomCategoryMapper {

    /**
     * 获取待办事项分类列表
     *
     * @param userId 用户id
     * @return 待办事项分类列表
     */
    List<CategoryListResource> listCategories(Integer userId);

}
