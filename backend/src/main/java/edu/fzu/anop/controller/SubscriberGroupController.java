package edu.fzu.anop.controller;

import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/sub/groups")
public class SubscriberGroupController {
    @Autowired
    GroupService groupService;

    @GetMapping()
    public Object getGroups(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(groupService.listUserSubscribeGroupInfo(page));
    }
}
