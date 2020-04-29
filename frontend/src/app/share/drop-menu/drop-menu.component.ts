import { Component } from '@angular/core';
import { UserCenterService } from '../../share/service/user-center.service';
import { UserInfo } from '../../share/model/user-info';

@Component({
  selector: 'app-drop-menu',
  templateUrl: './drop-menu.component.html'
})
export class DropMenuComponent {
  public user: UserInfo = {
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
    this.service.getConfig().subscribe(data => this.user = data);
  }

  signOut() {
    this.service.signOut();
  }
}
