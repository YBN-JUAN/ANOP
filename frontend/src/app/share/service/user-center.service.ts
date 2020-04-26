import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserInfo } from '../model/user-info';

@Injectable({
  providedIn: 'root'
})
export class UserCenterService {
  public url: string = 'http://localhost:8080/v1/';
  storageOk: boolean = false;

  constructor(public http:HttpClient) {
  }

  getConfig(){
    return this.http.get<UserInfo>(this.url + "profile");
  }

  storageUser() {
    if (this.storageOk) { return; }
    this.http.get<UserInfo>(this.url + "profile").subscribe(data => (
      localStorage.setItem('userid', String(data.id)),
        localStorage.setItem('username', String(data.userName))
    ))
    this.storageOk = true;
  }

  updateUserInfo(nickName: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.http.put(this.url + "profile", {nickname:nickName}, httpOptions)
      .subscribe(response => {
        console.log(response);
      });
  }

  resetPassword(newPassword: string, oldPassword: string, errorInput: boolean) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.http.post(this.url + "account/password",{newPassword:newPassword, oldPassword:oldPassword}, httpOptions)
      .subscribe(
        data => {
          console.log(data);
          window.alert("修改成功，请重新登录！");
          //signOut();
        },
        error => {
          console.log(error);
          window.alert("原密码输入错误，请重新输入！");
          }
      );
    }
}
