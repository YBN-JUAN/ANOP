package edu.fzu.anop;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Category;
import edu.fzu.anop.resource.*;
import edu.fzu.anop.service.CategoryService;
import edu.fzu.anop.util.MockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryServiceTest {

    @Resource(name = "categoryServiceImpl")
    private CategoryService categoryService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("admin");
    }

    @Test
    @Transactional
    void addCategory() {
        CategoryAddResource resource = new CategoryAddResource();
        Category category;

        resource.setTypeName("新分类");
        category = categoryService.addCategory(resource);
        assertThat(category.getTypeName()).isEqualTo(resource.getTypeName());
    }

    @Test
    void listCategories() {
        PageParmResource pageParmResource = new PageParmResource();
        PageInfo<List<CategoryListResource>> categories;

        categories = categoryService.listCategories(pageParmResource);
        assertThat(categories.getList()).hasSize(3);
    }

    @Test
    void getCategory() {
        Category category;

        // 返回id=1的分类
        category = categoryService.getCategory(1);
        assertThat(category.getId()).isEqualTo(1);
        assertThat(category.getTypeName()).isEqualTo("啊打发打发");

        // 不存在id=7的分类
        category = categoryService.getCategory(7);
        assertThat(category).isNull();
    }

    @Test
    @Transactional
    void updateCategory() {
        Category oldCategory, newCategory;
        CategoryUpdateResource updateResource = new CategoryUpdateResource();
        int affectedRow;

        // 更新id=1的分类，预期结果：更新成功
        oldCategory = categoryService.getCategory(1);
        updateResource.setTypeName("新名称");
        affectedRow = categoryService.updateCategory(oldCategory, updateResource);
        newCategory = categoryService.getCategory(1);
        assertThat(affectedRow).isEqualTo(1);
        assertThat(newCategory.getTypeName()).isEqualTo(updateResource.getTypeName());

        // 更新id=3的分类，预期结果：操作被拒绝
        oldCategory = categoryService.getCategory(3);
        updateResource.setTypeName("新名称");
        affectedRow = categoryService.updateCategory(oldCategory, updateResource);
        assertThat(affectedRow).isEqualTo(-1);

    }

    @Test
    @Transactional
    void deleteCategory() {
        int affectedRow;
        Category category;

        // 删除id=1的分类，预期结果：更新成功
        affectedRow = categoryService.deleteCategory(1);
        category = categoryService.getCategory(1);
        assertThat(affectedRow).isEqualTo(1);

        assertThat(category).isNull();

        // 删除id=3的分类，预期结果：操作被拒绝
        affectedRow = categoryService.deleteCategory(3);
        category = categoryService.getCategory(3);
        assertThat(affectedRow).isEqualTo(-1);
        assertThat(category).isNotNull();
    }

    @Test
    void listAllCategory() {
        List<CategoryListResource> resources = categoryService.listAllCategories();
        assertThat(resources).hasSize(3);
    }

    @Test
    void listTodoByCategoryId() {
        PageInfo<List<TodoResource>> pageInfo;
        PageParmResource page = new PageParmResource();
        pageInfo = categoryService.listTodoByCategoryId(1, page);
        List<List<TodoResource>> todos = pageInfo.getList();
        assertThat(todos).hasSize(1);
    }
}
