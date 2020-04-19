package edu.fzu.anop.service.impl;

import edu.fzu.anop.mapper.CategoryMapper;
import edu.fzu.anop.pojo.Category;
import edu.fzu.anop.resource.CategoryAddResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.CategoryService;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public Category addCategory(CategoryAddResource resource) {
        Category category = PropertyMapperUtil.map(resource, Category.class);
        category.setUserId(SecurityUtil.getLoginUser(User.class).getId());
        categoryMapper.insert(category);
        return category;
    }
}
