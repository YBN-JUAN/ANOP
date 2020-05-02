package edu.fzu.anop.controller;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.GroupUser;
import edu.fzu.anop.resource.GroupUserResource;
import edu.fzu.anop.resource.GroupUserUpdateResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.service.GroupUserService;
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
 * 通知群组成员控制器
 *
 * @author Xue_Feng
 */
@Api(value = "通知群组成员", tags = {"通知群组成员"})
@RestController
@RequestMapping("/v1/pub/groups/{gid}/users")
public class GroupUserController {
    @Autowired
    GroupUserService groupUserService;

    @ApiOperation("获取指定通知群组的成员信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = GroupUserResource.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 404, message = "成员不存在", response = Message.class)
    })
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

    @ApiOperation("获取指定通知群组的成员列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = PageInfo.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class)
    })
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

    @ApiOperation("更新指定通知群组成员权限")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
        @ApiImplicitParam(name = "uid", value = "成员id", required = true, dataType = "int")
    })
    @ApiResponses({
        @ApiResponse(code = 204, message = "更新成功"),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 404, message = "成员不存在", response = Message.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class)
    })
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

    @ApiOperation("删除指定通知群组成员")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
        @ApiImplicitParam(name = "uid", value = "成员id", required = true, dataType = "int")
    })
    @ApiResponses({
        @ApiResponse(code = 204, message = "更新成功"),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 404, message = "成员不存在", response = Message.class),
    })
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
