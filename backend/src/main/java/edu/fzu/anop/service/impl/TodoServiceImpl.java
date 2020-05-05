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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ZYF
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class TodoServiceImpl implements TodoService {

    private static final byte UN_COMPLETED = 0;

    private static final byte UN_CHECKED = 0;

    private static final byte CHECKED = 1;

    private static final byte COMPLETE = 0;

    private static final byte IMPORTANT = 1;

    private static final byte FAVORITE = 2;

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
    public int checkTodo(Todo todo, TodoFlagResource resource) {

        if (!todo.getUserId().equals(SecurityUtil.getLoginUser(User.class).getId())) {
            return -1;
        }

        TodoExample example = new TodoExample();
        TodoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(todo.getId());

        if (resource.getFlag() == IMPORTANT) {
            todo.setIsImportant((todo.getIsImportant() == CHECKED) ? UN_CHECKED : CHECKED);
        }
        else if (resource.getFlag() == FAVORITE) {
            todo.setIsFavorite((todo.getIsFavorite() == CHECKED) ? UN_CHECKED : CHECKED);
        }
        else if (resource.getFlag() == COMPLETE) {
            todo.setIsCompleted((todo.getIsCompleted() == CHECKED) ? UN_CHECKED : CHECKED);
        }
        return todoMapper.updateByExampleSelective(todo, example);
    }

    @Override
    public Todo getTodo(int todoId) {
        return todoMapper.selectByPrimaryKey(todoId);
    }

    @Override
    public PageInfo<List<Todo>> listUserTodo(PageParmResource page, TodoFlagResource flagResource) {
        TodoExample todoExample = new TodoExample();

        TodoExample.Criteria criteria1 = todoExample.createCriteria();
        criteria1.andUserIdEqualTo(SecurityUtil.getLoginUser(User.class).getId())
                .andIsCompletedEqualTo((byte) 0)
                .andEndDateGreaterThan(new Date());

        if (flagResource.getFlag() == IMPORTANT) {
            criteria1.andIsImportantEqualTo((byte) 1);
        }
        else if (flagResource.getFlag() == FAVORITE) {
            criteria1.andIsFavoriteEqualTo((byte) 1);
        }

        TodoExample.Criteria criteria2 = todoExample.createCriteria();
        criteria2.andUserIdEqualTo(SecurityUtil.getLoginUser(User.class).getId())
                .andIsCompletedEqualTo((byte) 0)
                .andEndDateIsNull();

        if (flagResource.getFlag() == IMPORTANT) {
            criteria2.andIsImportantEqualTo((byte) 1);
        }
        else if (flagResource.getFlag() == FAVORITE) {
            criteria2.andIsFavoriteEqualTo((byte) 1);
        }

        todoExample.or(criteria2);

        PageSortHelper.pageAndSort(page, TodoResource.class);
        List<Todo> todos = todoMapper.selectByExample(todoExample);
        return new PageInfo(todos);
    }

    @Override
    public PageInfo<List<Todo>> listHistoryTodo(PageParmResource page) {
        TodoExample todoExample = new TodoExample();
        TodoExample.Criteria criteria = todoExample.createCriteria();
        criteria.andUserIdEqualTo(SecurityUtil.getLoginUser(User.class).getId());
        PageSortHelper.pageAndSort(page, TodoResource.class);
        List<Todo> todos = todoMapper.selectByExample(todoExample);
        return new PageInfo(todos);
    }
}
