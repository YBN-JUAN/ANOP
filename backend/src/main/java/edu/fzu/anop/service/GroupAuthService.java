package edu.fzu.anop.service;

public interface GroupAuthService {
    boolean canUpdateGroupInfo(int groupId);

    boolean canDeleteGroup(int groupId);

    boolean canUpdateGroupUserRole(int groupId);

    boolean canDeleteGroupUser(int groupId);

    boolean canHandleUserRequest(int groupId);

    boolean canAddNotification(int groupId);

    boolean canUpdateNotification(int groupId);

    boolean canDeleteNotification(int groupId);

    boolean canMarkNotification(int groupId);

    boolean canListReceiver(int groupId);

    boolean canTurnNotificationIntoTodo(int groupId);

    boolean canGetReceiverNotification(int groupId);

    boolean canQuitGroup(int groupId);
}
