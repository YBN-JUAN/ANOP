import { Component, OnInit } from '@angular/core';
import { UserCenterService } from '../../../share/service/user-center.service';
import { UserInfo } from '../../../share/model/UserInfo';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  public user:UserInfo;

  public nickname = "昵称";

  public creationTime = "注册时间xxxxx";

  constructor(
    public service:UserCenterService,
    public http:HttpClient
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
  }
}
