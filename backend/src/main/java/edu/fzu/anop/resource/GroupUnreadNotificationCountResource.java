package edu.fzu.anop.resource;

/**
 * 通知未读通知统计资源
 *
 * @author Xue_Feng
 */
public class GroupUnreadNotificationCountResource {
    private Integer groupId;
    private Long unreadCount;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Long getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Long unreadCount) {
        this.unreadCount = unreadCount;
    }
}
