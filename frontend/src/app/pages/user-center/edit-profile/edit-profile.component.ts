import { Component, OnInit } from '@angular/core';
import { UserCenterService } from '../../../share/service/user-center.service';
import { UserInfo } from '../../../share/model/user-info';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  public user: UserInfo = {
    id: 0,
    email: '',
    userName: '',
    nickName: '',
    creationTime: '',
    avatarUrl: '',
  };
  public avatarURL = '';

  constructor(
    public service:UserCenterService
  ) { }

  ngOnInit(): void {
    this.service.getConfig().subscribe(data => {
      this.user = data;
      this.user.avatarUrl = data.avatarUrl;
    });
  }

  onEditUsername() {
    document.getElementById('show').style.display='none';
    document.getElementById('hide').style.display='block';
  }

  onSubmit() {
    document.getElementById('hide').style.display='none';
    document.getElementById('show').style.display='block';
    this.service.updateUserInfo(this.user.nickName, this.user.avatarUrl);
  }

  onUrlUpload() {
    document.getElementById('upload-show').style.display='none';
    document.getElementById('upload-hide').style.display='block';
  }

  getAvatarUrl() {
    document.getElementById('upload-hide').style.display='none';
    document.getElementById('upload-show').style.display='block';
    this.service.updateUserInfo(this.user.nickName, this.avatarURL);
    this.service.getConfig().subscribe(data => {
      this.user.avatarUrl = data.avatarUrl;
    });
    window.location.reload();
  }
}
