package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.TodoAddResource;
import edu.fzu.anop.resource.TodoFlagResource;
import edu.fzu.anop.resource.TodoUpdateResource;

import java.util.List;

/**
 * @author ZYF
 */
public interface TodoService {
    /**
     * 添加待办
     *
     * @param resource 待办数据
     * @return 新的分类
     */
    Todo addTodo(TodoAddResource resource);

    /**
     * 删除指定待办
     *
     * @param todo 指定待办
     * @return 删除成功返回1，失败返回-1
     */
    int deleteTodo(Todo todo);

    /**
     * 更新指定待办
     *
     * @param oldTodo  指定待办
     * @param resource 新的待办数据
     * @return 更新成功返回1，失败返回-1
     */
    int updateTodo(Todo oldTodo, TodoUpdateResource resource);

    /**
     * 切换指定待办状态
     *
     * @param todo     指定待办
     * @param resource 标记，指定需要切换的状态类型
     * @return
     */
    int checkTodo(Todo todo, TodoFlagResource resource);

    /**
     * 获取指定id的待办
     *
     * @param todoId 待办id
     * @return 待办
     */
    Todo getTodo(int todoId);

    /**
     * 按类型获取分页获取用户的待办
     *
     * @param page         分页参数
     * @param flagResource 类型参数
     * @return 指定分页和类型的历史待办列表
     */
    PageInfo<List<Todo>> listUserTodo(PageParmResource page, TodoFlagResource flagResource);

    /**
     * 分页获取用户的历史待办
     *
     * @param page 分页参数
     * @return 指定分页的历史待办列表
     */
    PageInfo<List<Todo>> listHistoryTodo(PageParmResource page);
}
