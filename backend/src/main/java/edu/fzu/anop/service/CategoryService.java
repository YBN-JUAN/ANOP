package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Category;
import edu.fzu.anop.resource.CategoryAddResource;
import edu.fzu.anop.resource.CategoryListResource;
import edu.fzu.anop.resource.CategoryUpdateResource;
import edu.fzu.anop.resource.PageParmResource;

import java.util.List;

/**
 * @author ZYF
 */
public interface CategoryService {

    Category addCategory(CategoryAddResource resource);

    PageInfo<List<CategoryListResource>> listCategories(PageParmResource page);

    Category getCategory(Integer id);

    int updateCategory(Category oldCategory, CategoryUpdateResource resource);

    int deleteCategory(Integer id);
}
