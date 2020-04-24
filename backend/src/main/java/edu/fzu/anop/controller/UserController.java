package edu.fzu.anop.controller;

import edu.fzu.anop.resource.PasswordUpdateResource;
import edu.fzu.anop.resource.UserResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.UserService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping(path = "/user", method = {RequestMethod.GET, RequestMethod.POST})
    public Object user() {
        User loginUser = SecurityUtil.getLoginUser(User.class);
        UserResource resource = PropertyMapperUtil.map(loginUser, UserResource.class);
        return JsonResult.ok(resource);
    }

    @PostMapping(path = "/failed")
    public Object failed() {
        return JsonResult.unauthorized("用户名或者密码错误", null);
    }

    @PostMapping("v1/account/password")
    public Object resetPassword(
            @RequestBody @Valid PasswordUpdateResource resource,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }

        User user = SecurityUtil.getLoginUser(User.class);
        if( !user.getPassword().equals(resource.getOldPassword()) ) {
            return JsonResult.badRequest("The old password is incorrect", null);
        }

        int result = userService.resetPassword(resource.getNewPassword());
        if(result == -1) {
            return JsonResult.internalServerError("Failed to update password", null);
        }
        return JsonResult.noContent().build();
    }

}
