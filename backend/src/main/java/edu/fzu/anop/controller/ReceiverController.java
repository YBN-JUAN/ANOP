package edu.fzu.anop.controller;

import edu.fzu.anop.resource.ReceiverAddResource;
import edu.fzu.anop.service.ReceiverService;
import edu.fzu.anop.util.JsonResult;
import edu.fzu.anop.util.Message;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 已读通知控制器
 *
 * @author Xue_Feng
 */
@Api(value = "已读通知", tags = {"已读通知"})
@RestController
@RequestMapping("/v1/sub/groups/{gid}/notifications/{nid}/readers")
public class ReceiverController {
    @Autowired
    ReceiverService receiverService;

    @ApiOperation(value = "已读指定订阅者订阅通知群组的通知")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "gid", value = "通知群组群号", required = true, dataType = "int"),
        @ApiImplicitParam(name = "nid", value = "通知id", required = true, dataType = "int")
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "操作成功"),
        @ApiResponse(code = 403, message = "用户没有权限", response = Message.class),
    })
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
