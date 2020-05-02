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

    /**
     * 添加分类
     *
     * @param resource 分类参数
     * @return 新的分类
     */
    Category addCategory(CategoryAddResource resource);

    /**
     * 分页列出用户的分类
     *
     * @param page 分页参数
     * @return 指定分页的分类列表
     */
    PageInfo<List<CategoryListResource>> listCategories(PageParmResource page);

    /**
     * 获取指定id的分类
     *
     * @param id 分类id
     * @return 指定id的分类，若不存在指定id的分类返回<code>null<code/>
     */
    Category getCategory(Integer id);

    /**
     * 更新指定分类
     *
     * @param oldCategory 指定分类
     * @param resource    新的分类数据
     * @return 更新成功返回1，失败返回-1
     */
    int updateCategory(Category oldCategory, CategoryUpdateResource resource);

    /**
     * 删除指定id的分类
     *
     * @param id 分类id
     * @return 删除成功返回1，失败返回-1
     */
    int deleteCategory(Integer id);

    /**
     * 获取用户所有的分类
     *
     * @return 分类列表
     */
    List<CategoryListResource> listAllCategories();
}
