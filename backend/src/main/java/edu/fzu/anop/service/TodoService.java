package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.TodoAddResource;
import edu.fzu.anop.resource.TodoUpdateResource;

import java.util.List;

public interface TodoService {
    Todo addTodo(TodoAddResource resource);

    int deleteTodo(Todo todo);

    int updateTodo(Todo oldTodo, TodoUpdateResource resource);

    int checkTodo(Todo todo);

    Todo getTodo(int todoId);

    PageInfo<List<Todo>> getUserTodoList(PageParmResource resource);

}
