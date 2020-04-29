export class Group {
  id: number;
  title: string;
  userId: number;
  nickname: string;
  avatarUrl: string;
  remark: string;
  creationDate: string;
  permission: number;

  getPermissionDescription(){
    switch (this.permission) {
      case 0:
        return '需要管理员审核';
      case 1:
        return '允许任何人加入'
      case 2:
        return '不允许任何人加入'
      default:
        return '未知'
    }
  }

}

