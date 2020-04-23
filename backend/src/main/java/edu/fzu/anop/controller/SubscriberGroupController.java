package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.resource.GroupUnreadNotificationCountResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.service.NotificationService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/sub/groups")
public class SubscriberGroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    NotificationService notificationService;

    @GetMapping()
    public Object getGroups(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(groupService.listUserSubscribeGroupInfo(page));
    }

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
