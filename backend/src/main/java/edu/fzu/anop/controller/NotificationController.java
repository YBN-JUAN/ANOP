package edu.fzu.anop.controller;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Notification;
import edu.fzu.anop.resource.*;
import edu.fzu.anop.service.NotificationService;
import edu.fzu.anop.service.ReceiverService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/pub/groups/{gid}/notifications")
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @Autowired
    ReceiverService receiverService;

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

    @GetMapping()
    public Object getNotifications(
        @Valid PageParmResource page,
        BindingResult bindingResult,
        @PathVariable("gid") int groupId) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        PageInfo<List<NotificationResource>> listPageInfo = notificationService.listNotification(page, groupId);
        if (listPageInfo == null) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.ok(listPageInfo);
    }

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

    @GetMapping("/{nid}/readers")
    public Object getReaders(@PathVariable("gid") int groupId, @PathVariable("nid") int notificationId) {
        List<ReceiverResource> resources = receiverService.listReceiver(notificationId, groupId);
        if (resources == null) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.ok(resources);
    }

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
