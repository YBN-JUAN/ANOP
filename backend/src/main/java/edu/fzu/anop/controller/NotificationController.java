package edu.fzu.anop.controller;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Notification;
import edu.fzu.anop.resource.*;
import edu.fzu.anop.service.NotificationService;
import edu.fzu.anop.service.ReceiverService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import edu.fzu.anop.util.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 通知控制器
 *
 * @author Xue_Feng
 */
@Api(value = "通知", tags = {"通知"})
@RestController
@RequestMapping("/v1/pub/groups/{gid}/notifications")
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @Autowired
    ReceiverService receiverService;

    @ApiOperation(value = "发布通知")
    @ApiResponses({
        @ApiResponse(code = 200, message = "发布成功"),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class)
    })
    @PostMapping
    public Object addNotification(
        @RequestBody @Valid NotificationAddResource resource,
        BindingResult bindingResult,
        @PathVariable("gid") int groupId) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        int result = notificationService.addNotification(resource, groupId);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.ok().build();
    }

    @ApiOperation(value = "获取通知")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
        @ApiImplicitParam(name = "nid", value = "通知id", required = true, dataType = "int")
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = NotificationResource.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 404, message = "通知不存在", response = Message.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class)
    })
    @GetMapping("/{nid}")
    public Object getNotification(@PathVariable("gid") int groupId, @PathVariable("nid") int notificationId) {
        Notification notification = notificationService.getNotification(notificationId, groupId);
        if (notification == null) {
            return JsonResult.notFound("notification was not found", null);
        }
        NotificationResource notificationInfo = notificationService.getNotificationInfo(notificationId, groupId);
        if (notificationInfo == null) {
            return JsonResult.forbidden(null, null);
        }
        return notificationInfo;
    }

    @ApiOperation(value = "获取指定通知群组的通知列表")
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
        PageInfo<List<NotificationResource>> listPageInfo = notificationService.listNotificationInfo(page, groupId);
        if (listPageInfo == null) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.ok(listPageInfo);
    }

    @ApiOperation(value = "更新指定通知群组的通知")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
        @ApiImplicitParam(name = "nid", value = "通知id", required = true, dataType = "int")
    })
    @ApiResponses({
        @ApiResponse(code = 204, message = "更新成功"),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 404, message = "通知不存在", response = Message.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class)
    })
    @PatchMapping("/{nid}")
    public Object updateNotification(
        @RequestBody @Valid NotificationUpdateResource resource,
        BindingResult bindingResult,
        @PathVariable("gid") int groupId,
        @PathVariable("nid") int notificationId) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Notification notification = notificationService.getNotification(notificationId, groupId);
        if (notification == null) {
            return JsonResult.notFound("notification was not found", null);
        }
        int result = notificationService.updateNotification(notification, resource);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "删除指定通知群组的通知")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
        @ApiImplicitParam(name = "nid", value = "通知id", required = true, dataType = "int")
    })
    @ApiResponses({
        @ApiResponse(code = 204, message = "删除成功"),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 404, message = "通知不存在", response = Message.class),
    })
    @DeleteMapping("/{nid}")
    public Object deleteNotification(@PathVariable("gid") int groupId, @PathVariable("nid") int notificationId) {
        Notification notification = notificationService.getNotification(notificationId, groupId);
        if (notification == null) {
            return JsonResult.notFound("notification was not found", null);
        }
        int result = notificationService.deleteNotification(notification);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "获取指定通知群组的通知的已读未读成员列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
        @ApiImplicitParam(name = "nid", value = "通知id", required = true, dataType = "int")
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = PageInfo.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class)
    })
    @GetMapping("/{nid}/readers")
    public Object getReaders(@PathVariable("gid") int groupId, @PathVariable("nid") int notificationId) {
        List<ReceiverResource> resources = receiverService.listReceiver(notificationId, groupId);
        if (resources == null) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.ok(resources);
    }

    @ApiOperation(value = "将指定通知群组的通知转化为待办事项")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
        @ApiImplicitParam(name = "nid", value = "通知id", required = true, dataType = "int")
    })
    @ApiResponses({
        @ApiResponse(code = 204, message = "转化成功", response = PageInfo.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class)
    })
    @PostMapping("/{nid}/asTodo")
    public Object asTodo(@PathVariable("gid") int groupId, @PathVariable("nid") int notificationId) {
        Notification notification = notificationService.getNotification(notificationId, groupId);
        if (notification == null) {
            return JsonResult.notFound("notification was not found", null);
        }
        int result = notificationService.asTodo(notification);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }
}
