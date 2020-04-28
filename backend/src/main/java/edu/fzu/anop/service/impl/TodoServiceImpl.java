package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.TodoMapper;
import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.pojo.example.TodoExample;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.TodoAddResource;
import edu.fzu.anop.resource.TodoResource;
import edu.fzu.anop.resource.TodoUpdateResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.TodoService;
import edu.fzu.anop.util.PageSortHelper;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ZYF
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TodoServiceImpl implements TodoService {

    private static final byte UN_COMPLETED = 0;

    private static final byte COMPLETED = 1;

    @Resource
    TodoMapper todoMapper;

    @Override
    public Todo addTodo(TodoAddResource resource) {
        Todo todo = PropertyMapperUtil.map(resource, Todo.class);
        todo.setIsCompleted(UN_COMPLETED);
        todo.setUserId(SecurityUtil.getLoginUser(User.class).getId());
        todoMapper.insert(todo);
        return todo;
    }

    @Override
    public int deleteTodo(Todo todo) {

        if (!todo.getUserId().equals(SecurityUtil.getLoginUser(User.class).getId())) {
            return -1;
        }

        return todoMapper.deleteByPrimaryKey(todo.getId());
    }

    @Override
    public int updateTodo(Todo oldTodo, TodoUpdateResource resource) {

        if (!oldTodo.getUserId().equals(SecurityUtil.getLoginUser(User.class).getId())) {
            return -1;
        }

        TodoExample example = new TodoExample();
        TodoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(oldTodo.getId());
        Todo newTodo = PropertyMapperUtil.map(resource, Todo.class);
        newTodo.setId(oldTodo.getId());
        newTodo.setUserId(oldTodo.getUserId());
        newTodo.setIsCompleted(oldTodo.getIsCompleted());

        return todoMapper.updateByExample(newTodo, example);
    }

    @Override
    public int checkTodo(Todo todo) {

        if (!todo.getUserId().equals(SecurityUtil.getLoginUser(User.class).getId())) {
            return -1;
        }

        TodoExample example = new TodoExample();
        TodoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(todo.getId());
        todo.setIsCompleted((byte) ((todo.getIsCompleted() == COMPLETED) ? UN_COMPLETED : COMPLETED));
        return todoMapper.updateByExampleSelective(todo, example);
    }

    @Override
    public Todo getTodo(int todoId) {
        return todoMapper.selectByPrimaryKey(todoId);
    }

    @Override
    public PageInfo<List<Todo>> listUserTodo(PageParmResource page) {
        TodoExample todoExample = new TodoExample();
        TodoExample.Criteria criteria = todoExample.createCriteria();
        criteria.andUserIdEqualTo(SecurityUtil.getLoginUser(User.class).getId());
        PageSortHelper.pageAndSort(page, TodoResource.class);
        List<Todo> todos = todoMapper.selectByExample(todoExample);
        return new PageInfo(todos);
    }

    @Override
    public PageInfo<List<Todo>> listHistoryTodo(PageParmResource page) {
        TodoExample todoExample = new TodoExample();
        TodoExample.Criteria criteria = todoExample.createCriteria();
        criteria.andUserIdEqualTo(SecurityUtil.getLoginUser(User.class).getId())
                .andEndDateLessThan(new Date());
        PageSortHelper.pageAndSort(page, TodoResource.class);
        List<Todo> todos = todoMapper.selectByExample(todoExample);
        return new PageInfo(todos);
    }
}
