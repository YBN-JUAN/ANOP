package edu.fzu.anop;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.TodoAddResource;
import edu.fzu.anop.resource.TodoFlagResource;
import edu.fzu.anop.resource.TodoUpdateResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.TodoService;
import edu.fzu.anop.util.MockUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TodoServiceTest {

    static final byte UN_CHECKED = 0;

    static final byte CHECKED = 1;

    static final byte COMPLETE = 0;

    static final byte IMPORTANT = 1;

    static final byte FAVORITE = 2;

    static final int MY_TODO_ID = 1;

    static final int OTHER_TODO_ID = 2;

    static final int GOOD_RESULT = 1;

    static final int BAD_RESULT = -1;

    @Resource(name = "todoServiceImpl")
    TodoService todoService;

    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("admin");
    }

    @Test
    @Transactional
    void addTodo() {
        Todo todo;
        TodoAddResource resource = new TodoAddResource();
        resource.setTitle("新的待办");
        resource.setContent("内容");
        resource.setIsFavorite(UN_CHECKED);
        resource.setIsImportant(UN_CHECKED);

        // 添加新的待办事项 预期结果：添加成功
        todo = todoService.addTodo(resource);
        assertThat(todo.getTitle()).isEqualTo(resource.getTitle());
        assertThat(todo.getContent()).isEqualTo(resource.getContent());
        assertThat(todo.getUserId()).isEqualTo(SecurityUtil.getLoginUser(User.class).getId());
    }

    @Test
    @Transactional
    void deleteTodo() {
        Todo todo;
        int result;

        todo = todoService.getTodo(MY_TODO_ID);
        result = todoService.deleteTodo(todo);
        todo = todoService.getTodo(MY_TODO_ID);
        assertThat(result).isEqualTo(GOOD_RESULT);
        assertThat(todo).isNull();

        todo = todoService.getTodo(OTHER_TODO_ID);
        result = todoService.deleteTodo(todo);
        assertThat(result).isEqualTo(BAD_RESULT);
        assertThat(todoService.getTodo(OTHER_TODO_ID)).isNotNull();
    }

    @Test
    @Transactional
    void updateTodo() {
        Todo oldTodo, newTodo;
        TodoUpdateResource resource = new TodoUpdateResource();
        int result;

        resource.setId(MY_TODO_ID);
        resource.setTitle("newTitle");
        resource.setContent("newContent");
        resource.setIsFavorite(UN_CHECKED);
        resource.setIsImportant(CHECKED);

        // 更新id=1的待办 预期结果：更新成功
        oldTodo = todoService.getTodo(MY_TODO_ID);
        result = todoService.updateTodo(oldTodo, resource);
        newTodo = todoService.getTodo(MY_TODO_ID);
        assertThat(result).isEqualTo(GOOD_RESULT);
        assertThat(newTodo).isNotNull()
                .hasFieldOrPropertyWithValue("title", resource.getTitle())
                .hasFieldOrPropertyWithValue("content", resource.getContent())
                .hasFieldOrPropertyWithValue("beginDate", null)
                .hasFieldOrPropertyWithValue("isFavorite", UN_CHECKED)
                .hasFieldOrPropertyWithValue("isImportant", CHECKED)
                .hasFieldOrPropertyWithValue("isCompleted", UN_CHECKED);

        // 更新id=2的待办 预期结果：操作被拒绝
        oldTodo = todoService.getTodo(OTHER_TODO_ID);
        result = todoService.updateTodo(oldTodo, resource);
        newTodo = todoService.getTodo(OTHER_TODO_ID);
        assertThat(result).isEqualTo(BAD_RESULT);
        assertThat(newTodo).isEqualToComparingOnlyGivenFields(oldTodo);
    }

    @Test
    @Transactional
    void checkTodo() {
        Todo oldTodo, newTodo;
        TodoFlagResource resource = new TodoFlagResource();
        int result;

        resource.setFlag(COMPLETE);
        oldTodo = todoService.getTodo(MY_TODO_ID);
        result = todoService.checkTodo(oldTodo, resource);
        newTodo = todoService.getTodo(MY_TODO_ID);
        assertThat(result).isEqualTo(GOOD_RESULT);
        assertThat(newTodo).isNotNull()
                .hasFieldOrPropertyWithValue("isCompleted", CHECKED);


        resource.setFlag(IMPORTANT);
        oldTodo = todoService.getTodo(MY_TODO_ID);
        result = todoService.checkTodo(oldTodo, resource);
        newTodo = todoService.getTodo(MY_TODO_ID);
        assertThat(result).isEqualTo(GOOD_RESULT);
        assertThat(newTodo).isNotNull()
                .hasFieldOrPropertyWithValue("isImportant", UN_CHECKED);

        resource.setFlag(FAVORITE);
        oldTodo = todoService.getTodo(MY_TODO_ID);
        result = todoService.checkTodo(oldTodo, resource);
        newTodo = todoService.getTodo(MY_TODO_ID);
        assertThat(result).isEqualTo(GOOD_RESULT);
        assertThat(newTodo).isNotNull()
                .hasFieldOrPropertyWithValue("isFavorite", UN_CHECKED);


        oldTodo = todoService.getTodo(OTHER_TODO_ID);
        result = todoService.checkTodo(oldTodo, resource);
        assertThat(result).isEqualTo(BAD_RESULT);
    }

    @Test
    void getTodo() {
        Todo todo;

        todo = todoService.getTodo(MY_TODO_ID);
        assertThat(todo).isNotNull().hasFieldOrPropertyWithValue("id", MY_TODO_ID);

        todo = todoService.getTodo(100);
        assertThat(todo).isNull();
    }

    @Test
    void listUserTodo() {
        PageInfo<List<Todo>> pageInfo;
        PageParmResource page = new PageParmResource();
        TodoFlagResource flagResource = new TodoFlagResource();

        page.setOrderBy("id desc");
        flagResource.setFlag(COMPLETE);
        pageInfo = todoService.listUserTodo(page, flagResource);
        assertThat(pageInfo.getList()).hasSize(3);
    }

    @Test
    void listHistoryTodo() {
        PageInfo<List<Todo>> pageInfo;
        PageParmResource page = new PageParmResource();

        pageInfo = todoService.listHistoryTodo(page);
        List<List<Todo>> todos = pageInfo.getList();
        assertThat(todos).hasSize(3);
    }
}
