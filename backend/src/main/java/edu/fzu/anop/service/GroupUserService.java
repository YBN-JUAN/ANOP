package edu.fzu.anop.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.pojo.UserRequest;

import java.util.List;

public interface GroupUserService {
    boolean hasAdminRole(int groupId, int userId);

    int addGroupUser(int groupId, int userId);

    int deleteGroupUser(int groupId, int userId);

    int updateGroupRole(int groupId, int userId, int role);

    PageInfo<List<UserRequest>> getUserRequest();

    int acceptOrDenyUserRequest(UserRequest request);
}
