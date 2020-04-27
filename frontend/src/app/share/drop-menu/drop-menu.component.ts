import { Component } from '@angular/core';
import { UserCenterService } from '../../share/service/user-center.service';
import { UserInfo } from '../../share/model/user-info';
import { Router} from '@angular/router';

@Component({
  selector: 'app-drop-menu',
  templateUrl: './drop-menu.component.html'
})
export class DropMenuComponent {
  visible = false;
  public user: UserInfo;

  constructor(
    private service: UserCenterService,
    private route: Router
  ){ }

  ngOnInit(): void {
    this.service.getConfig().subscribe(data => this.user = data);
  }

  signOut() {
    this.route.navigateByUrl('/welcome/login');
  }
}
