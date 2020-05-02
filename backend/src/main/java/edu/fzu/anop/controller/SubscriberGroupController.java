package edu.fzu.anop.controller;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.resource.GroupUnreadNotificationCountResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.service.NotificationService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import edu.fzu.anop.util.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 订阅者通知群组控制器
 *
 * @author Xue_Feng
 */
@Api(value = "订阅者通知群组", tags = {"订阅者通知群组"})
@RestController
@RequestMapping("/v1/sub/groups")
public class SubscriberGroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    NotificationService notificationService;

    @ApiOperation(value = "获取用户订阅的通知群组列表")
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = PageInfo.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class),
    })
    @GetMapping()
    public Object getGroups(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(groupService.listUserSubscribeGroupInfo(page));
    }

    @ApiOperation(value = "获取用户订阅的通知群组未读通知数")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "通知群组群号", required = true, dataType = "int"),
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = GroupUnreadNotificationCountResource.class),
        @ApiResponse(code = 404, message = "通知群组不存在", response = Message.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
    })
    @GetMapping("/{id}/notifications/unread_count")
    public Object getGroupUnreadNotificationCount(@PathVariable("id") int groupId) {
        Group group = groupService.getGroup(groupId);
        if (group == null) {
            return JsonResult.notFound("group was not found", null);
        }
        GroupUnreadNotificationCountResource resource = notificationService.countGroupUnreadNotification(groupId);
        if (resource == null) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.ok(resource);
    }

    @ApiOperation(value = "取消订阅的通知群组")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "通知群组群号", required = true, dataType = "int"),
    })
    @ApiResponses({
        @ApiResponse(code = 204, message = "操作成功"),
        @ApiResponse(code = 404, message = "通知群组不存在", response = Message.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
    })
    @DeleteMapping("/{id}")
    public Object quitGroup(@PathVariable("id") int groupId) {
        Group group = groupService.getGroup(groupId);
        if (group == null) {
            return JsonResult.notFound("group was not found", null);
        }
        int result = groupService.quitGroup(group);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }
}
