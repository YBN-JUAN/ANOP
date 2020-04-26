import { Component, OnInit } from '@angular/core';
import { UserCenterService } from '../../../share/service/user-center.service';
import { UserInfo } from '../../../share/model/user-info';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  public user:UserInfo;

  constructor(
    public service:UserCenterService
  ) { }

  ngOnInit(): void {
    this.service.getConfig().subscribe(data => this.user = data);
  }

  onEditUsername() {
    document.getElementById('show').style.display='none';
    document.getElementById('hide').style.display='block';
  }

  onSubmit() {
    document.getElementById('hide').style.display='none';
    document.getElementById('show').style.display='block';
    this.service.updateUserInfo(this.user.nickName);
  }
}
