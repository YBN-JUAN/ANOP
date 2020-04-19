package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.CategoryMapper;
import edu.fzu.anop.mapper.CustomCategoryMapper;
import edu.fzu.anop.pojo.Category;
import edu.fzu.anop.resource.CategoryAddResource;
import edu.fzu.anop.resource.CategoryListResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.CategoryService;
import edu.fzu.anop.util.PageSortHelper;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Resource
    CustomCategoryMapper customCategoryMapper;

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
}
