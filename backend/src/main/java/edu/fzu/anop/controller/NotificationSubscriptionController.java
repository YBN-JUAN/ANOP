package edu.fzu.anop.controller;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Notification;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.ReceiverNotificationResource;
import edu.fzu.anop.service.NotificationService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import edu.fzu.anop.util.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 订阅者通知控制器
 *
 * @author Xue_Feng
 */
@Api(value = "订阅者通知", tags = {"订阅者通知"})
@RestController
@RequestMapping("/v1/sub/groups/{gid}/notifications")
public class NotificationSubscriptionController {
    @Autowired
    NotificationService notificationService;

    @ApiOperation(value = "获取指定订阅者订阅通知群组的通知")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
        @ApiImplicitParam(name = "nid", value = "通知id", required = true, dataType = "int")
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = ReceiverNotificationResource.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 404, message = "通知不存在", response = Message.class),
    })
    @GetMapping("/{nid}")
    public Object getNotification(@PathVariable("gid") int groupId, @PathVariable("nid") int notificationId) {
        Notification notification = notificationService.getNotification(notificationId, groupId);
        if (notification == null) {
            return JsonResult.notFound("notification was not found", null);
        }
        ReceiverNotificationResource notificationInfo = notificationService.getReceiverNotification(notificationId, groupId);
        if (notificationInfo == null) {
            return JsonResult.forbidden(null, null);
        }
        return notificationInfo;
    }

    @ApiOperation(value = "获取指定订阅者订阅通知群组的通知列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = PageInfo.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class)
    })
    @GetMapping()
    public Object getNotifications(
        @Valid PageParmResource page,
        BindingResult bindingResult,
        @PathVariable("gid") int groupId) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        PageInfo<List<ReceiverNotificationResource>> listPageInfo = notificationService.listReceiverNotification(page, groupId);
        if (listPageInfo == null) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.ok(listPageInfo);
    }
}
