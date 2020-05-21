package edu.fzu.anop.controller;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Category;
import edu.fzu.anop.resource.CategoryAddResource;
import edu.fzu.anop.resource.CategoryUpdateResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.CategoryService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import edu.fzu.anop.util.Message;
import edu.fzu.anop.util.SecurityUtil;
import io.swagger.annotations.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author ZYF
 */
@Api(tags = "待办事项分类")
@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    @Resource(name = "categoryServiceImpl")
    CategoryService categoryService;

    @ApiOperation(value = "添加待办事项分类", notes = "添加待办事项分类")
    @ApiResponses({
            @ApiResponse(code = 201, message = "成功创建", response = Category.class),
            @ApiResponse(code = 422, message = "参数未通过验证", response = Message.class)
    })
    @PostMapping()
    public Object addCateGory(
            @RequestBody @Valid CategoryAddResource resource, BindingResult bindingResult) throws URISyntaxException {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Category category = categoryService.addCategory(resource);
        return JsonResult.created(new URI("http://localhost:8080/v1/categories/" + category.getId())).body(category);
    }

    @ApiOperation(value = "获取当前用户的分类列表", notes = "获取当前用户的分类列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功获取", response = PageInfo.class),
            @ApiResponse(code = 422, message = "分页参数验证错误", response = Message.class)
    })
    @GetMapping()
    public Object listCategories(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(categoryService.listCategories(page));
    }

    @ApiOperation(value = "获取当前用户的所有分类", notes = "获取当前用户的所有分类")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功获取", response = List.class),
            @ApiResponse(code = 422, message = "参数未通过验证", response = Message.class)
    })
    @GetMapping("/all")
    public Object listAllCategories() {
        return JsonResult.ok(categoryService.listAllCategories());
    }

    @ApiOperation(value = "获取指定id的分类的基本信息", notes = "获取指定id的分类的基本信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功获取", response = Category.class),
            @ApiResponse(code = 404, message = "未找到指定id的分类", response = Message.class),
            @ApiResponse(code = 403, message = "没有此分类的访问权限", response = Message.class)
    })
    @GetMapping("/{id}")
    public Object getCategories(@PathVariable int id) {
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return JsonResult.notFound("category was not found", null);
        }
        if (!category.getUserId().equals(SecurityUtil.getLoginUser(User.class).getId())) {
            return JsonResult.forbidden("you have no permission to get this category", null);
        }
        return JsonResult.ok(category);
    }

    @ApiOperation(value = "更新指定id的分类", notes = "更新指定id的分类")
    @ApiResponses({
            @ApiResponse(code = 201, message = "更新成功"),
            @ApiResponse(code = 404, message = "未找到指定id的分类", response = Message.class),
            @ApiResponse(code = 403, message = "没有此分类的访问权限", response = Message.class),
            @ApiResponse(code = 422, message = "参数未通过验证", response = Message.class)
            })
    @PutMapping("/{id}")
    public Object updateCategories(
            @PathVariable int id,
            @RequestBody @Valid CategoryUpdateResource resource,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return JsonResult.notFound("category was not found", null);
        }
        int result = categoryService.updateCategory(category, resource);
        if (result == -1) {
            return JsonResult.forbidden("you have no permission to modify this category", null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "删除指定id的分类", notes = "删除操作同时会将分类所属所有的待办事项删除！！")
    @ApiResponses({
            @ApiResponse(code = 204, message = "删除成功"),
            @ApiResponse(code = 404, message = "未找到指定id的分类", response = Message.class),
            @ApiResponse(code = 403, message = "没有此分类的访问权限", response = Message.class)
    })
    @DeleteMapping("/{id}")
    public Object deleteCategories(@PathVariable int id) {
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return JsonResult.notFound("category was not found", null);
        }
        int result = categoryService.deleteCategory(id);
        if (result == -1) {
            return JsonResult.forbidden("you have no permission to delete this category", null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "获取指定id的分类下的所有待办事项", notes = "获取指定id的分类下的所有待办事项")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "categoryId", value = "分类id", dataType = "int")
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功获取", response = PageInfo.class),
            @ApiResponse(code = 422, message = "分页参数验证错误", response = Message.class),
            @ApiResponse(code = 404, message = "未找到指定id的分类", response = Message.class),
            @ApiResponse(code = 403, message = "没有此分类的访问权限", response = Message.class)
    })
    @GetMapping("/list/{categoryId}")
    public Object getTodoByCategoryId(@PathVariable int categoryId, @Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Category category = categoryService.getCategory(categoryId);
        if (category == null) {
            return JsonResult.notFound("category was not found", null);
        }
        if (!category.getUserId().equals(SecurityUtil.getLoginUser(User.class).getId())) {
            return JsonResult.forbidden("you have no permission to get the todos of this category", null);
        }
        return JsonResult.ok(categoryService.listTodoByCategoryId(categoryId, page));
    }
}
