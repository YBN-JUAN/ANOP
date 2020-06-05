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

export class PatchGroupModel {
  title: string;
  remark: string;
  permission: number;

  setValue(title: string, remark: string, permission: number) {
    this.title = title;
    this.remark = remark;
    this.permission = permission;
  }
}

export class UpdateUserInfo {
  isAdmin: number;
}

