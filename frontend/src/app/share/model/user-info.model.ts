export class UserInfoModel {
    id: number;
    email: string;
    userName: string;
    nickName: string;
    creationTime: string;
    avatarUrl: string;
}

export class GroupUser {
  userId: number;
  nickname: string;
  avatarUrl: string;
  isAdmin: number;
  isRead: number;
}
