package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.TodoMapper;
import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.pojo.example.TodoExample;
import edu.fzu.anop.resource.*;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.TodoService;
import edu.fzu.anop.util.PageSortHelper;
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
        Todo todo = PropertyMapperUtil.map(resource, Todo.class);
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

        TodoExample example = new TodoExample();
        TodoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(oldTodo.getId());
        Todo newTodo = PropertyMapperUtil.map(resource, Todo.class);
        newTodo.setId(oldTodo.getId());
        return todoMapper.updateByExampleSelective(newTodo, example);
    }

    @Override
    public int checkTodo(Todo oldTodo, TodoCheckResource resource) {
        TodoExample example = new TodoExample();
        TodoExample.Criteria criteria = example.createCriteria();
        Todo newTodo = PropertyMapperUtil.map(resource, Todo.class);
        criteria.andIdEqualTo(oldTodo.getId());
        return todoMapper.updateByExampleSelective(newTodo, example);
    }

    @Override
    public Todo getTodo(int todoId) {
        return todoMapper.selectByPrimaryKey(todoId);
    }

    @Override
    public PageInfo<List<Todo>> getUserTodoList(PageParmResource page) {
        TodoExample todoExample = new TodoExample();
        TodoExample.Criteria criteria = todoExample.createCriteria();
        criteria.andUserIdEqualTo(SecurityUtil.getLoginUser(User.class).getId());
        PageSortHelper.pageAndSort(page, TodoListResource.class);
        List<Todo> todos = todoMapper.selectByExample(todoExample);
        return new PageInfo(todos);
    }
}
