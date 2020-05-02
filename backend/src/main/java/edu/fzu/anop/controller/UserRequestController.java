package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.UserRequest;
import edu.fzu.anop.resource.GroupUnreadNotificationCountResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.UserRequestAddResource;
import edu.fzu.anop.resource.UserRequestUpdateResource;
import edu.fzu.anop.service.UserRequestService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import edu.fzu.anop.util.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "通知群组加入申请", tags = {"通知群组加入申请"})
@RestController
@RequestMapping("/v1/pub/requests")
public class UserRequestController {
    @Autowired
    UserRequestService userRequestService;

    @ApiOperation(value = "申请加入指定通知群组")
    @ApiResponses({
        @ApiResponse(code = 204, message = "申请成功"),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
    })
    @PostMapping()
    public Object addUserRequest(
        @RequestBody @Valid UserRequestAddResource resource,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        int result = userRequestService.addUserRequest(resource);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }

    @ApiOperation(value = "获取用户创建的通知群组加入申请列表")
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功"),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class),
    })
    @GetMapping()
    public Object getUserRequests(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(userRequestService.listUserRequest(page));
    }

    @ApiOperation(value = "获取用户管理的通知群组加入申请列表")
    @ApiResponses({
        @ApiResponse(code = 200, message = "获取成功"),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class),
    })
    @GetMapping("/manage")
    public Object getManageUserRequests(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(userRequestService.listManageUserRequest(page));
    }

    @ApiOperation(value = "同意或者拒绝加入申请")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "申请id", required = true, dataType = "int"),
    })
    @ApiResponses({
        @ApiResponse(code = 204, message = "操作成功", response = GroupUnreadNotificationCountResource.class),
        @ApiResponse(code = 404, message = "加入申请不存在", response = Message.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
        @ApiResponse(code = 422, message = "请求体参数验证错误", response = Message.class),
    })
    @PostMapping("/{id}")
    public Object acceptOrDenyUserRequest(
        @RequestBody @Valid UserRequestUpdateResource resource,
        BindingResult bindingResult,
        @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        UserRequest request = userRequestService.getUserRequest(id);
        if (request == null) {
            return JsonResult.notFound("user request was not found", null);
        }
        int result = userRequestService.acceptOrDenyUserRequest(request, resource.getIsAccepted());
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }
}
