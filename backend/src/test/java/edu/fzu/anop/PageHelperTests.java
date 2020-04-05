package edu.fzu.anop;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.UserInfoMapper;
import edu.fzu.anop.pojo.UserInfo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PageHelperTests {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserInfoMapper infoMapper;

    @Test
    public void TestPagination() {
        PageHelper.startPage(1, 1);
        List<UserInfo> userInfos = infoMapper.selectByExample(null);
        PageInfo pageInfo = new PageInfo(userInfos);
        logger.info(String.valueOf(pageInfo.getPages()), pageInfo);

    }
}
