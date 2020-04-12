package edu.fzu.anop;

import edu.fzu.anop.mapper.CategoryMapper;
import edu.fzu.anop.mapper.TodoMapper;
import edu.fzu.anop.mapper.UserInfoMapper;
import edu.fzu.anop.pojo.Category;
import edu.fzu.anop.pojo.Todo;
import edu.fzu.anop.pojo.UserInfo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTests {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserInfoMapper infoMapper;

    @Autowired
    TodoMapper todoMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Test
    public void TestUserInfo() {
        List<UserInfo> userInfos = infoMapper.selectByExample(null);
        userInfos.forEach((i) -> logger.info(i.toString()));
        Category category = categoryMapper.selectByPrimaryKey(1);
        System.out.println(category);
        Todo todo = todoMapper.selectByPrimaryKey(1);
        System.out.println(todo);
    }
}
