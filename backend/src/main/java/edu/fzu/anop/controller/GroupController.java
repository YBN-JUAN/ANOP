package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.resource.GroupAddResource;
import edu.fzu.anop.resource.GroupResource;
import edu.fzu.anop.resource.GroupUpdateResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import edu.fzu.anop.util.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 通知群组控制器
 *
 * @author Xue_Feng
 */
@Api(value = "通知群组", tags = {"通知群组"})
@RestController
@RequestMapping("/v1/pub/groups")
public class GroupController {
    @Autowired
    GroupService groupService;

    @ApiOperation(value = "创建通知群组")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "resource", value = "创建通知群组参数", required = true, dataType = "GroupAddResource"),
    })
    @ApiResponses({
        @ApiResponse(code = 201, message = "创建成功", response = Group.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class)
    })
    @PostMapping()
    public Object addGroup(
        @RequestBody @Valid GroupAddResource resource, BindingResult bindingResult) throws URISyntaxException {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Group group = groupService.addGroup(resource);
        return JsonResult.created(new URI("http://localhost:8080/v1/pub/groups/" + group.getId())).body(group);
    }

    @ApiOperation(value = "获取一个通知群组")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "通知群组群号", required = true, dataType = "int"),
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = GroupResource.class),
        @ApiResponse(code = 404, message = "通知群组不存在", response = Message.class)
    })
    @GetMapping("/{id}")
    public Object getGroup(@PathVariable("id") int id) {
        GroupResource group = groupService.getGroupInfo(id);
        if (group == null) {
            return JsonResult.notFound("group was not found", null);
        }
        return JsonResult.ok(group);
    }

    @ApiOperation(value = "获取用户创建的通知群组列表")
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = GroupResource.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class),
    })
    @GetMapping()
    public Object getCreateGroups(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(groupService.listUserCreateGroupInfo(page));
    }

    @ApiOperation(value = "获取用户管理的通知群组列表")
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功", response = GroupResource.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class),
    })
    @GetMapping("/manage")
    public Object getManageGroups(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(groupService.listUserManageGroupInfo(page));
    }

    @ApiOperation(value = "更新指定通知群组")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "通知群组群号", required = true, dataType = "int"),
        @ApiImplicitParam(name = "GroupUpdateResource", value = "更新通知群组参数", required = true, dataType = "GroupUpdateResource"),
    })
    @ApiResponses({
        @ApiResponse(code = 204, message = "更新成功"),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class),
        @ApiResponse(code = 403, message = "用户不是通知群组的创建者或者管理员", response = Message.class)
    })
    @PatchMapping("/{id}")
    public Object updateGroup(
        @RequestBody @Valid GroupUpdateResource resource,
        @PathVariable("id") int id,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Group group = groupService.getGroup(id);
        if (group == null) {
            return JsonResult.notFound("group was not found", null);
        }
        int result = groupService.updateGroup(group, resource);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "解散指定通知群组")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "通知群组群号", required = true, dataType = "int"),
    })
    @ApiResponses({
        @ApiResponse(code = 204, message = "删除成功"),
        @ApiResponse(code = 404, message = "通知群组不存在", response = Message.class),
        @ApiResponse(code = 403, message = "用户不是通知群组的创建者或者管理员", response = Message.class)
    })
    @DeleteMapping("/{id}")
    public Object deleteGroup(@PathVariable("id") int id) {
        Group group = groupService.getGroup(id);
        if (group == null) {
            return JsonResult.notFound("group was not found", null);
        }
        int result = groupService.deleteGroup(group);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }
}
