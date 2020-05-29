import { Component } from '@angular/core';
import { UserCenterService } from '../service/user-center.service';
import { UserInfoModel } from '../model/user-info.model';

@Component({
  selector: 'app-drop-menu',
  templateUrl: './drop-menu.component.html'
})
export class DropMenuComponent {
  public user: UserInfoModel = {
    id: 0,
    email: '',
    userName: '',
    nickName: '',
    creationTime: '',
    avatarUrl: '',
  };
  visible = false;

  constructor(
    private service: UserCenterService
  ){ }

  OnInit(): void {
    this.service.getConfig().subscribe(data => {
      this.user = data;
      const avatar = data.avatarUrl;
      if(avatar.startsWith('https://')) {
        this.user.avatarUrl = data.avatarUrl;
      }
      else {
        this.user.avatarUrl = 'http://' + data.avatarUrl;
      }
    });
  }

  signOut() {
    this.service.signOut();
  }
}
