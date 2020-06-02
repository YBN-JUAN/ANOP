export class GroupInfoModel {
  id: number;
  title: string;
  userId: number;
  nickname: string;
  avatarUrl: string;
  remark: string;
  creationDate: string;
  permission: number;
  unreadCount: number;
}

export class GroupUpdateInfo {
  permission: number;
  remark: string;
  title: string;
}

export class UpdateUserInfo {
  isAdmin: number;
}

