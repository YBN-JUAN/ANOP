package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.TodoAddResource;
import edu.fzu.anop.resource.TodoFlagResource;
import edu.fzu.anop.resource.TodoUpdateResource;
import edu.fzu.anop.service.TodoService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import io.swagger.annotations.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author ZYF
 */
@Api(tags = "待办事项")
@RestController
@RequestMapping("v1/todos")
public class TodoController {

    @Resource(name = "todoServiceImpl")
    TodoService todoService;

    @ApiOperation(value = "添加待办事项", notes = "添加待办事项")
    @ApiResponses({
            @ApiResponse(code = 201, message = "成功创建"),
            @ApiResponse(code = 422, message = "参数未通过验证")
    })
    @PostMapping()
    public Object addTodo(
            @RequestBody @Valid TodoAddResource resource, BindingResult bindingResult) throws URISyntaxException {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Todo todo = todoService.addTodo(resource);
        return JsonResult.created(new URI("http://localhost:8080/v1/todos/" + todo.getId())).body(todo);
    }

    @ApiOperation(value = "获取待办事项列表", notes = "获取指定类型的待办事项列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "flag", value = "0:所有 1:重要 2:收藏", required = false, dataType = "int")
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功获取"),
            @ApiResponse(code = 422, message = "参数未通过验证")
    })
    @GetMapping()
    public Object getTodoList(@Valid TodoFlagResource flagResource, @Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(todoService.listUserTodo(page, flagResource));
    }

    @ApiOperation(value = "更新待办事项的信息", notes = "更新待办事项的信息（不包括完成状态）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "待办事项id", required = true, dataType = "int"),
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "更新成功"),
            @ApiResponse(code = 404, message = "未找到指定id的待办事项"),
            @ApiResponse(code = 403, message = "没有此待办事项的访问权限")
    })
    @PutMapping("/{id}")
    public Object updateTodo(
            @RequestBody @Valid TodoUpdateResource resource,
            @PathVariable int id,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Todo todo = todoService.getTodo(id);

        if (todo == null) {
            return JsonResult.notFound("todoItem was not found", null);
        }
        int result = todoService.updateTodo(todo, resource);
        if (result == -1) {
            return JsonResult.forbidden("you have no permission to modify this todoitem", null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "切换待办事项状态", notes = "切换待办事项状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "待办事项id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "flag", value = "0:完成 1:重要 2:收藏", required = false, dataType = "int"),
    })
    @PutMapping("/check/{id}")
    public Object checkTodo(@PathVariable int id, @Valid TodoFlagResource resource) {
        Todo todo = todoService.getTodo(id);
        if (todo == null) {
            return JsonResult.notFound("todoItem was not found", null);
        }
        int result = todoService.checkTodo(todo, resource);
        if (result == -1) {
            return JsonResult.forbidden("you have no permission to complete this todoitem", null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "删除指定id的待办事项", notes = "删除指定id的待办事项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "待办事项id", required = true, dataType = "int"),
    })
    @ApiResponses({
            @ApiResponse(code = 204, message = "删除成功"),
            @ApiResponse(code = 404, message = "未找到指定id的待办事项"),
            @ApiResponse(code = 403, message = "没有此待办事项的访问权限")
    })
    @DeleteMapping("/{id}")
    public Object deleteTodo(@PathVariable int id) {
        Todo todo = todoService.getTodo(id);
        if (todo == null) {
            return JsonResult.notFound("todoItem was not found", null);
        }
        int result = todoService.deleteTodo(todo);
        if (result == -1) {
            return JsonResult.forbidden("you have no permission to delete this todoitem", null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "获取指定id的待办事项信息", notes = "获取指定id的待办事项信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "待办事项id", required = true, dataType = "int"),
    })
    @GetMapping("/{id}")
    public Object getTodo(@PathVariable int id) {
        Todo todo = todoService.getTodo(id);
        if (todo == null) {
            return JsonResult.notFound("todoItem was not found", null);
        }
        return JsonResult.ok(todo);
    }

    @ApiOperation(value = "获取历史待办事项列表", notes = "获取历史待办事项列表")
    @GetMapping("/histories")
    public Object getHistoryTodoList(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(todoService.listHistoryTodo(page));
    }
}
