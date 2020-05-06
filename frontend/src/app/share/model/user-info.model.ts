export class UserInfoModel {
    id: number;
    email: string;
    userName: string;
    nickName: string;
    creationTime: string;
    avatarUrl: string;
}

export class GroupUser {
  groupId: number;
  id: number;
  nickname: string;
  avatarUrl: string;
  isAdmin: number;
}
