package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.TodoAddResource;
import edu.fzu.anop.resource.TodoCheckResource;
import edu.fzu.anop.resource.TodoUpdateResource;
import edu.fzu.anop.service.TodoService;
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
@RequestMapping("v1")
public class TodoController {

    @Resource(name = "todoServiceImpl")
    TodoService todoService;

    @ApiOperation(value = "添加待办事项", notes = "添加待办事项")
    @PostMapping("todos")
    public Object addTodo(
            @RequestBody @Valid TodoAddResource resource, BindingResult bindingResult) throws URISyntaxException {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Todo todo = todoService.addTodo(resource);
        return JsonResult.created(new URI("http://localhost:8080/v1/todos/" + todo.getId())).body(todo);
    }

    @ApiOperation(value = "获取待办事项列表", notes = "获取所有的待办事项列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBy", value = "排序规则", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的项目数", required = true, dataType = "Integer")
    })
    @GetMapping("todos")
    public Object getTodoList(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(todoService.getUserTodoList(page));
    }

    @ApiOperation(value = "更新待办事项的信息", notes = "更新待办事项的信息（不包括完成状态）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "待办事项id", required = true, dataType = "Integer"),
    })
    @PutMapping("todos/{id}")
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
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "更新待办事项完成标记", notes = "勾选待办事项时触发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "待办事项id", required = true, dataType = "Integer"),
    })
    @PutMapping("todos/check/{id}")
    public Object checkTodo(
            @RequestBody @Valid TodoCheckResource resource,
            @PathVariable int id,
            BindingResult bindingResult) {
        Todo todo = todoService.getTodo(id);
        if (todo == null) {
            return JsonResult.notFound("todoItem was not found", null);
        }
        int result = todoService.checkTodo(todo, resource);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }
}
