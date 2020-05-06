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

  ngOnInit(): void {
    this.service.getConfig().subscribe(data => {
      this.user = data;
    });
  }

  signOut() {
    this.service.signOut();
  }
}
