package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.CustomUserRequestMapper;
import edu.fzu.anop.mapper.GroupUserMapper;
import edu.fzu.anop.mapper.UserRequestMapper;
import edu.fzu.anop.pojo.GroupUser;
import edu.fzu.anop.pojo.UserRequest;
import edu.fzu.anop.pojo.example.UserRequestExample;
import edu.fzu.anop.resource.GroupUserAddResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.resource.UserRequestAddResource;
import edu.fzu.anop.resource.UserRequestResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.GroupAuthService;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.service.GroupUserService;
import edu.fzu.anop.service.UserRequestService;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 通知群组加入请求业务逻辑默认实现
 *
 * @author Xue_Feng
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class UserRequestServiceImpl implements UserRequestService {
    private static final byte ACCEPT = 1;
    private static final byte DENY = 2;
    private static final byte PENDING = 0;
    private static final byte COMMON_ROLE = 0;

    @Autowired
    CustomUserRequestMapper customUserRequestMapper;
    @Autowired
    GroupUserService groupUserService;
    @Autowired
    GroupService groupService;
    @Autowired
    UserRequestMapper userRequestMapper;
    @Autowired
    GroupUserMapper groupUserMapper;
    @Autowired
    GroupAuthService authService;

    private int addGroupUser(int userId, int groupId) {
        GroupUserAddResource resource = new GroupUserAddResource();
        resource.setUserId(userId);
        resource.setGroupId(groupId);
        GroupUser newGroupUser = PropertyMapperUtil.map(resource, GroupUser.class);
        newGroupUser.setIsAdmin(COMMON_ROLE);
        return groupUserMapper.insert(newGroupUser);
    }

    @Override
    public int addUserRequest(UserRequestAddResource resource) {
        int currentUserId = SecurityUtil.getLoginUser(User.class).getId();
        if (!groupService.hasGroup(resource.getGroupId())) {
            return -1;
        }
        if (groupUserService.isInGroup(currentUserId, resource.getGroupId())) {
            return -1;
        }
        if (groupService.isPrivateGroup(resource.getGroupId())) {
            return -1;
        }
        if (groupService.isPublicGroup(resource.getGroupId())) {
            return addGroupUser(currentUserId, resource.getGroupId());
        }
        UserRequestExample requestExample = new UserRequestExample();
        UserRequestExample.Criteria criteria = requestExample.createCriteria();
        criteria.andUserIdEqualTo(currentUserId);
        criteria.andGroupIdEqualTo(resource.getGroupId());
        List<UserRequest> requests = userRequestMapper.selectByExample(requestExample);
        UserRequest userRequest;
        boolean isNew = false;
        if (requests.size() > 0) {
            userRequest = requests.get(0);
            if (userRequest.getIsAccepted() == PENDING) {
                return 1;
            }
        } else {
            isNew = true;
            userRequest = PropertyMapperUtil.map(resource, UserRequest.class);
            userRequest.setUserId(currentUserId);
        }
        userRequest.setIsAccepted(PENDING);
        userRequest.setRequestTime(new Date());
        if (isNew) {
            return userRequestMapper.insert(userRequest);
        }
        return userRequestMapper.updateByPrimaryKey(userRequest);
    }

    @Override
    public UserRequest getUserRequest(int requestId) {
        UserRequest userRequest = userRequestMapper.selectByPrimaryKey(requestId);
        return userRequest;
    }

    @Override
    public PageInfo<List<UserRequestResource>> listUserRequest(PageParmResource page) {
        List<UserRequestResource> resources = customUserRequestMapper.listUserRequest(SecurityUtil.getLoginUser(User.class).getId());
        return new PageInfo(resources);
    }

    @Override
    public PageInfo<List<UserRequestResource>> listManageUserRequest(PageParmResource page) {
        List<UserRequestResource> resources = customUserRequestMapper.listManageUserRequest(SecurityUtil.getLoginUser(User.class).getId());
        return new PageInfo(resources);
    }

    @Override
    public int acceptOrDenyUserRequest(UserRequest request, byte isAccepted) {
        if (request.getIsAccepted() != PENDING) {
            return -1;
        }
        if (!groupService.hasGroup(request.getGroupId())) {
            return -1;
        }
        if (isAccepted == DENY) {
            request.setIsAccepted(DENY);
            return userRequestMapper.updateByPrimaryKey(request);
        }
        if (isAccepted == ACCEPT) {
            if (!authService.canHandleUserRequest(request.getGroupId())) {
                return -1;
            }
            if (!groupUserService.isInGroup(request.getUserId(), request.getGroupId())) {
                if (!groupService.isPrivateGroup(request.getGroupId())) {
                    addGroupUser(request.getUserId(), request.getGroupId());
                }
            }
            request.setIsAccepted(ACCEPT);
            return userRequestMapper.updateByPrimaryKey(request);
        } else {
            return -1;
        }
    }
}