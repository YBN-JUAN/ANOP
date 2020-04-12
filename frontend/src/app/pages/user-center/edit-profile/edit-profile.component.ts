import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  public loginName = "登录名xxxxx";

  public userId = "用户IDxxxxxx";

  public userName = "User";

  public email = "注册邮箱xxxx";

  public date = "注册时间xxxxx";

  constructor() { }

  ngOnInit(): void {
  }

}
