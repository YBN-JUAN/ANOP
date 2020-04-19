package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.Category;
import edu.fzu.anop.resource.CategoryAddResource;
import edu.fzu.anop.resource.CategoryUpdateResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.service.CategoryService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    @Resource(name = "categoryServiceImpl")
    CategoryService categoryService;

    @ApiOperation(value = "添加待办事项分类", notes = "添加待办事项分类")
    @PostMapping()
    public Object addCateGory(
            @RequestBody @Valid CategoryAddResource resource, BindingResult bindingResult) throws URISyntaxException {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Category category = categoryService.addCategory(resource);
        return JsonResult.created(new URI("http://localhost:8080/v1/categories/" + category.getId())).body(category);
    }

    // 写分类列表0接口
    @ApiOperation(value = "获取当前用户的分类列表", notes = "获取当前用户的分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBy", value = "排序规则", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的项目数", required = true, dataType = "int")
    })
    @GetMapping()
    public Object listCategories(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(categoryService.listCategories(page));
    }

    @ApiOperation(value = "获取指定id的分类的基本信息", notes = "获取指定id的分类的基本信息")
    @GetMapping("/{id}")
    public Object getCategories(@PathVariable int id) {
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return JsonResult.notFound("category was not found", null);
        }
        return JsonResult.ok(category);
    }

    @ApiOperation(value = "更新指定id的分类", notes = "更新指定id的分类")
    @PutMapping("/{id}")
    public Object updateCategories(
            @RequestBody @Valid CategoryUpdateResource resource,
            @PathVariable int id
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return JsonResult.notFound("todoItem was not found", null);
        }
        int result = categoryService.updateCategory(category, resource);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "删除指定id的分类", notes = "删除操作同时会将分类所属所有的待办事项删除！！")
    @DeleteMapping("/{id}")
    public Object deleteCategories(@PathVariable int id) {
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return JsonResult.notFound("category was not found", null);
        }
        int result = categoryService.deleteCategory(id);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }
}
