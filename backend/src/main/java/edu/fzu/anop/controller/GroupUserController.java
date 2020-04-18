package edu.fzu.anop.controller;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.GroupUser;
import edu.fzu.anop.resource.GroupAddResource;
import edu.fzu.anop.resource.GroupUserResource;
import edu.fzu.anop.resource.GroupUserUpdateResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.service.GroupUserService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/pub/groups/{gid}/users")
public class GroupUserController {
    @Autowired
    GroupUserService groupUserService;

    @GetMapping("/{uid}")
    public Object getGroupUser(@PathVariable("gid") int groupId, @PathVariable("uid") int userId) {
        GroupUser groupUser = groupUserService.getGroupUser(userId, groupId);
        if (groupUser == null) {
            return JsonResult.notFound("groupUser was not found", null);
        }
        GroupUserResource userResource = groupUserService.getGroupUserInfo(userId, groupId);
        if (userResource == null) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.ok(userResource);
    }

    @GetMapping()
    public Object getGroupUsers(
        @Valid PageParmResource page,
        BindingResult bindingResult,
        @PathVariable("gid") int groupId) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        PageInfo<List<GroupUserResource>> listPageInfo = groupUserService.listGroupUser(page, groupId);
        if (listPageInfo == null) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.ok(listPageInfo);
    }

    @PatchMapping("/{uid}")
    public Object updateGroupUserRole(
        @RequestBody @Valid GroupUserUpdateResource resource,
        BindingResult bindingResult,
        @PathVariable("gid") int groupId,
        @PathVariable("uid") int userId) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        GroupUser groupUser = groupUserService.getGroupUser(userId, groupId);
        if (groupUser == null) {
            return JsonResult.notFound("groupUser was not found", null);
        }
        int result = groupUserService.updateGroupUserRole(groupUser, resource);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }

    @DeleteMapping("/{uid}")
    public Object deleteGroupUser(@PathVariable("gid") int groupId, @PathVariable("uid") int userId) {
        GroupUser groupUser = groupUserService.getGroupUser(userId, groupId);
        if (groupUser == null) {
            return JsonResult.notFound("groupUser was not found", null);
        }
        int result = groupUserService.deleteGroupUser(groupUser);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }
}
