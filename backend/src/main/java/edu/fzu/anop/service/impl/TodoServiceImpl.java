package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.TodoMapper;
import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.TodoAddResource;
import edu.fzu.anop.resource.TodoUpdateResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.TodoService;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Resource
    TodoMapper todoMapper;

    @Override
    public Todo addTodo(@RequestBody @Valid TodoAddResource resource) {
        Todo todo = PropertyMapperUtil.map(resource, Todo.class);;
        todo.setIsCompleted(new Byte("0"));
        todo.setUserId(SecurityUtil.getLoginUser(User.class).getId());
        todoMapper.insert(todo);
        return todo;
    }

    @Override
    public int deleteTodo(Todo todo) {
        return 0;
    }

    @Override
    public int updateTodo(Todo oldTodo, TodoUpdateResource resource) {
        return 0;
    }

    @Override
    public Todo getTodo(int todoId) {
        return null;
    }

    @Override
    public PageInfo<List<Todo>> getUserTodos(PageParmResource resource) {
        return null;
    }
}
