package edu.fzu.anop.controller;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Notification;
import edu.fzu.anop.resource.NotificationResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.ReceiverNotificationResource;
import edu.fzu.anop.service.NotificationService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/sub/groups/{gid}/notifications")
public class NotificationSubscriptionController {
    @Autowired
    NotificationService notificationService;

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
