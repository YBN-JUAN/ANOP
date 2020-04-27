import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserInfo } from '../model/user-info';
import { Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserCenterService {
  public url: string = 'http://localhost:8080/v1/';
  storageOk: boolean = false;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(
    public http:HttpClient,
    private route: Router
    ) { }

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
    this.http.put(this.url + "profile", {nickname:nickName}, this.httpOptions)
      .subscribe(response => {
        console.log(response);
      });
  }

  resetPassword(newPassword: string, oldPassword: string, errorInput: boolean) {
    this.http.post(this.url + "account/password",{newPassword:newPassword, oldPassword:oldPassword}, this.httpOptions)
      .subscribe(
        data => {
          console.log(data);
          window.alert("修改成功，请重新登录！");
          this.signOut();
        },
        error => {
          console.log(error);
          window.alert("原密码输入错误，请重新输入！");
          }
      );
    }

    signOut() {
      this.http.post(this.url + "signout", {}, this.httpOptions)
      .subscribe(response => {
        console.log(response);
        localStorage.removeItem('userid');
        localStorage.removeItem('username');
        this.route.navigateByUrl('/welcome/login');
      });
    }
}
