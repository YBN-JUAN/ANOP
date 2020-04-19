package edu.fzu.anop.service;

import edu.fzu.anop.pojo.Category;
import edu.fzu.anop.resource.CategoryAddResource;

public interface CategoryService {

    Category addCategory(CategoryAddResource resource);

    
}
