package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.CategoryMapper;
import edu.fzu.anop.mapper.CustomCategoryMapper;
import edu.fzu.anop.mapper.TodoMapper;
import edu.fzu.anop.pojo.Category;
import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.pojo.example.TodoExample;
import edu.fzu.anop.resource.*;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.CategoryService;
import edu.fzu.anop.util.PageSortHelper;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZYF
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Resource
    CustomCategoryMapper customCategoryMapper;

    @Resource
    TodoMapper todoMapper;

    @Override
    public Category addCategory(CategoryAddResource resource) {
        Category category = PropertyMapperUtil.map(resource, Category.class);
        category.setUserId(SecurityUtil.getLoginUser(User.class).getId());
        categoryMapper.insert(category);
        return category;
    }

    @Override
    public PageInfo<List<CategoryListResource>> listCategories(PageParmResource page) {
        PageSortHelper.pageAndSort(page, CategoryListResource.class);
        List<CategoryListResource> categories = customCategoryMapper.listCategories(
                SecurityUtil.getLoginUser(User.class).getId()
        );
        return new PageInfo(categories);
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateCategory(Category oldCategory, CategoryUpdateResource resource) {

        if (!oldCategory.getUserId().equals(SecurityUtil.getLoginUser(User.class).getId())) {
            return -1;
        }

        Category newCategory = PropertyMapperUtil.map(resource, Category.class);
        newCategory.setId(oldCategory.getId());
        return categoryMapper.updateByPrimaryKeySelective(newCategory);
    }

    @Override
    public int deleteCategory(Integer id) {

        Category category = categoryMapper.selectByPrimaryKey(id);

        if (!category.getUserId().equals(SecurityUtil.getLoginUser(User.class).getId())) {
            return -1;
        }

        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CategoryListResource> listAllCategories() {
        int userId = SecurityUtil.getLoginUser(User.class).getId();
        List<CategoryListResource> categories = customCategoryMapper.listCategories(userId);
        return categories;
    }

    @Override
    public PageInfo<List<TodoResource>> listTodoByCategoryId(Integer categoryId, PageParmResource page) {
        TodoExample todoExample = new TodoExample();

        TodoExample.Criteria criteria1 = todoExample.createCriteria();
        criteria1.andUserIdEqualTo(SecurityUtil.getLoginUser(User.class).getId())
                .andCategoryIdEqualTo(categoryId);

        PageSortHelper.pageAndSort(page, TodoResource.class);
        List<Todo> todos = todoMapper.selectByExample(todoExample);
        return new PageInfo(todos);
    }
}
