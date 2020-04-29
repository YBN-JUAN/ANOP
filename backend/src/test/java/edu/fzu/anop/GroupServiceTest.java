package edu.fzu.anop;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.resource.GroupAddResource;
import edu.fzu.anop.resource.GroupResource;
import edu.fzu.anop.resource.GroupUpdateResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.util.MockUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class GroupServiceTest {
    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("user");
    }

    Logger logger = LoggerFactory.getLogger(GroupServiceTest.class);

    @Autowired
    GroupService groupService;

    @Test
    @Transactional
    @Rollback
    void TestDenyOperation() {
        Group group = new Group();

        final int badResult = -1;
        int result;

        group.setId(14);
        result = groupService.quitGroup(group);
        Assertions.assertEquals(badResult, result, "用户不是该群成员，却可以退出该群");
        result = groupService.deleteGroup(group);
        Assertions.assertEquals(badResult, result, "用户不是群主，却可以删除该群");
        result = groupService.updateGroup(group, new GroupUpdateResource());
        Assertions.assertEquals(badResult, result, "用户不是群主或管理员，却可以更新该群");


        group.setId(9);
        result = groupService.deleteGroup(group);
        Assertions.assertEquals(badResult, result, "用户不是群主，却可以删除该群");
        result = groupService.updateGroup(group, new GroupUpdateResource());
        Assertions.assertEquals(badResult, result, "用户不是群主或管理员，却可以更新该群");


        group.setId(1);
        result = groupService.deleteGroup(group);
        Assertions.assertEquals(badResult, result, "用户不是群主，却删除可以该群");
    }

    @Test
    @Transactional
    @Rollback
    void TestAcceptOperation() {
        GroupAddResource groupAddResource = new GroupAddResource();
        groupAddResource.setPermission((byte) 0);
        groupAddResource.setRemark("123");
        groupAddResource.setTitle("123");

        Group group = groupService.addGroup(groupAddResource);
        Assertions.assertNotNull(group.getId(), "用户无法创建通知群组");
        Group group1 = groupService.getGroup(group.getId());
        Assertions.assertEquals(group.getId(), group1.getId());

        GroupResource groupInfo = groupService.getGroupInfo(group.getId());
        Assertions.assertNotNull(groupInfo, "用户无法获取通知群组");
        Assertions.assertEquals(group.getId(), groupInfo.getId());

        PageInfo<List<GroupResource>> listPageInfo = groupService.listUserSubscribeGroupInfo(new PageParmResource());
        Assertions.assertNotNull(listPageInfo, "用户无法获取订阅的通知群组");
        Assertions.assertEquals(1, listPageInfo.getList().size());

        PageInfo<List<GroupResource>> listPageInfo1 = groupService.listUserCreateGroupInfo(new PageParmResource());
        Assertions.assertNotNull(listPageInfo1, "用户无法获取创建的通知群组");
        Assertions.assertEquals(3, listPageInfo1.getList().size());

        PageInfo<List<GroupResource>> listPageInfo2 = groupService.listUserManageGroupInfo(new PageParmResource());
        Assertions.assertNotNull(listPageInfo2, "用户无法获管理的通知群组");
        Assertions.assertEquals(1, listPageInfo2.getList().size());

        group.setId(1);
        int result = groupService.quitGroup(group);
        Assertions.assertTrue(result > 0, "用户是该群成员，却无法退出通知群组");

        group.setId(groupInfo.getId());
        GroupUpdateResource resource = new GroupUpdateResource();
        resource.setPermission((byte) 2);
        result = groupService.updateGroup(group, resource);
        Assertions.assertTrue(result > 0, "用户是该群管理员或者群主，却无法修改通知群组");

        result = groupService.deleteGroup(group);
        Assertions.assertTrue(result > 0, "用户是该群群主，却无法删除通知群组");
    }

}
