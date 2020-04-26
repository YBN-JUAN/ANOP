package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.UserRequest;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.UserRequestAddResource;
import edu.fzu.anop.resource.UserRequestResource;

import java.util.List;

/**
 * 通知群组加入请求业务逻辑
 *
 * @author Xue_Feng
 */
public interface UserRequestService {
    /**
     * 发送加入申请到指定通知群组
     *
     * @param resource 申请信息参数
     * @return 如果指定通知群组不存在、指定通知群组加入权限设置为不允许任何人加入、
     * 当前登录用户已经在指定通知群组中返回<code>-1</code>，否则返回数据库受影响行数
     */
    int addUserRequest(UserRequestAddResource resource);

    /**
     * 获取指定加入申请
     *
     * @param requestId 加入申请id
     * @return 加入申请
     */
    UserRequest getUserRequest(int requestId);

    /**
     * 获取登录用户创建的通知群组的加入申请列表
     *
     * @param page 分页参数
     * @return 加入申请列表
     */
    PageInfo<List<UserRequestResource>> listUserRequest(PageParmResource page);

    /**
     * 获取登录用户管理的通知群组的加入申请列表
     *
     * @param page 分页参数
     * @return 加入申请列表
     */
    PageInfo<List<UserRequestResource>> listManageUserRequest(PageParmResource page);

    /**
     * 处理加入申请
     *
     * @param request    加入申请
     * @param isAccepted 是否同意
     * @return 如果指定通知群组不存在、指定通知群组加入权限设置为不允许任何人加入、
     * 申请用户已经在指定通知群组中、参数<code>isAccepted</code>取值不是1或2、
     * 或者请求<code>request</code>已经被处理过返回<code>-1</code>，
     * 当前登录用户不是指定通知群组的创建者或者管理员返回<code>-1</code>，否则返回数据库受影响行数
     */
    int acceptOrDenyUserRequest(UserRequest request, byte isAccepted);
}
