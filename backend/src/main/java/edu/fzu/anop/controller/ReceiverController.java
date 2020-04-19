package edu.fzu.anop.controller;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.resource.*;
import edu.fzu.anop.service.ReceiverService;
import edu.fzu.anop.util.BindingResultUtil;
import edu.fzu.anop.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/sub/groups/{gid}/notifications/{nid}/readers")
public class ReceiverController {
    @Autowired
    ReceiverService receiverService;

    @PostMapping
    public Object addReader(
        @PathVariable("gid") int groupId,
        @PathVariable("nid") int notificationId) {
        ReceiverAddResource resource = new ReceiverAddResource();
        resource.setGroupId(groupId);
        resource.setNotificationId(notificationId);
        int result = receiverService.addReceiver(resource);
        if (result == -1) {
            return JsonResult.forbidden(null, null);
        }
        return JsonResult.ok().build();
    }

}
