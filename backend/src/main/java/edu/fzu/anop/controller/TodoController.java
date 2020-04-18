package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.resource.TodoAddResource;
import edu.fzu.anop.service.TodoService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("v1")
public class TodoController {

    @Resource(name = "todoServiceImpl")
    TodoService todoService;

    @PostMapping("todos")
    public Object addTodo(
            @RequestBody @Valid TodoAddResource resource, BindingResult bindingResult) throws URISyntaxException {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Todo todo = todoService.addTodo(resource);
        return JsonResult.created(new URI("http://localhost:8080/v1/todos/" + todo.getId())).body(todo);
    }


}
