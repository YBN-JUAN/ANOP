package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.UserRequest;
import edu.fzu.anop.resource.UserRequestAddResource;
import edu.fzu.anop.resource.UserRequestResource;
import edu.fzu.anop.resource.PageParmResource;

import java.util.List;

public interface UserRequestService {
    int addUserRequest(UserRequestAddResource resource);

    UserRequest getUserRequest(int requestId);

    PageInfo<List<UserRequestResource>> listUserRequest(PageParmResource page);

    PageInfo<List<UserRequestResource>> listManageUserRequest(PageParmResource page);

    int acceptOrDenyUserRequest(UserRequest request, byte isAccepted);
}
