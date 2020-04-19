package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.UserRequest;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.UserRequestAddResource;
import edu.fzu.anop.resource.UserRequestUpdateResource;
import edu.fzu.anop.service.UserRequestService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/pub/requests")
public class UserRequestController {
    @Autowired
    UserRequestService userRequestService;

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

    @GetMapping()
    public Object getUserRequests(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(userRequestService.listUserRequest(page));
    }

    @GetMapping("/manage")
    public Object getManageUserRequests(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(userRequestService.listManageUserRequest(page));
    }

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
