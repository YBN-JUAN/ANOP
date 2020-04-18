package edu.fzu.anop.service;

public interface GroupAuthService {
    boolean canUpdateGroupInfo(int groupId);

    boolean canDeleteGroup(int groupId);

    boolean canUpdateGroupUserRole(int groupId);

    boolean canDeleteGroupUser(int groupId);

    boolean canHandleUserRequest(int groupId);
}
