export class GroupInfoModel {
  id: number;
  title: string;
  userId: number;
  nickname: string;
  avatarUrl: string;
  remark: string;
  creationDate: string;
  permission: number;
  private _unreadCount: number;

  get unreadCount(): number {
    return this._unreadCount;
  }

  set unreadCount(value: number) {
    this._unreadCount = value;
  }
}

export class GroupUpdateInfo {
  permission: number;
  remark: string;
  title: string;
}

export class UpdateUserInfo {
  isAdmin: number;
}

