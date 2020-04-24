package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.CategoryListResource;

import java.util.List;

/**
 * @author ZYF
 */
public interface CustomCategoryMapper {

    List<CategoryListResource> listCategories(Integer userId);

}
