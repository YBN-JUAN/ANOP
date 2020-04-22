package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.UserInfo;
import edu.fzu.anop.resource.UserInfoResource;
import edu.fzu.anop.resource.UserInfoUpdateResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.UserInfoService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/profile")
    public Object getUserInfo() {
        User loginUser = SecurityUtil.getLoginUser(User.class);
        UserInfoResource resource = userInfoService.getUserInfoResource(loginUser.getId());
        if(resource == null) {
            return JsonResult.notFound("userInfo was not found", null);
        }
        return JsonResult.ok(resource);
    }

    @PutMapping("/profile")
    public Object updateUserInfo(
            @RequestBody @Valid UserInfoUpdateResource resource,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        User loginUser = SecurityUtil.getLoginUser(User.class);
        UserInfo userInfo = userInfoService.getUserInfoByUserId(loginUser.getId());

        if (userInfo == null) {
            return JsonResult.notFound("userInfo was not found", null);
        }
        int result = userInfoService.updateUserInfo(userInfo, resource);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.noContent().build();
    }

}
