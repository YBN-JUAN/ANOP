package edu.fzu.anop.controller;

import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.resource.GroupAddResource;
import edu.fzu.anop.resource.GroupUpdateResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/v1/pub/groups")
public class GroupController {
    @Autowired
    GroupService groupService;

    @PostMapping()
    public Object addGroup(
        @RequestBody @Valid GroupAddResource resource, BindingResult bindingResult) throws URISyntaxException {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        Group group = groupService.addGroup(resource);
        return JsonResult.created(new URI("http://localhost:8080/v1/pub/groups/" + group.getId())).body(group);
    }

    @GetMapping("/{id}")
    public Object getCreteGroup(@PathVariable("id") int id) {
        Group group = groupService.getGroup(id);
        if (group == null) {
            return JsonResult.notFound("group was not found", null);
        }
        return JsonResult.ok(group);
    }

    @GetMapping()
    public Object getCreateGroups(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(groupService.getUserCreateGroups(page));
    }

    @GetMapping("/manage")
    public Object getManageGroups(@Valid PageParmResource page, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return JsonResult.unprocessableEntity("error in validating", BindingResultUtil.getErrorList(bindingResult));
        }
        return JsonResult.ok(groupService.getUserManageGroups(page));
    }

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
