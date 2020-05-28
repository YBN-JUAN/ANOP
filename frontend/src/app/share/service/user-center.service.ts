import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserInfoModel} from '../model/user-info.model';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserCenterService {
  public user: UserInfoModel;
  public url: string = 'http://localhost:8080/v1/usr/';
  storageOk: boolean = false;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(
    private http: HttpClient,
    private route: Router
  ) {
  }

  getConfig(): Observable<UserInfoModel> {
    return this.http.get<UserInfoModel>(this.url + "profile");
  }

  storageUser() {
    if (this.storageOk) {
      return;
    }
    this.http.get<UserInfoModel>(this.url + "profile").subscribe(data => (
      localStorage.setItem('userid', String(data.id)),
        localStorage.setItem('username', String(data.userName))
    ))
    this.storageOk = true;
  }

  updateUserInfo(nickName: string, avatarUrl: string) {
    this.http.put(this.url + "profile", {nickname: nickName, avatarUrl: avatarUrl}, this.httpOptions)
      .subscribe(
        response => {
          console.log(response);
          window.alert("修改成功！");
        },
        error => {
          console.log(error);
          window.alert("修改失败！");
        }
      );
  }

  resetPassword(newPassword: string, oldPassword: string) {
    this.http.post(this.url + "account/password", {
      newPassword: newPassword,
      oldPassword: oldPassword
    }, this.httpOptions)
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
    this.http.post("http://localhost:8080/logout", {}, this.httpOptions)
      .subscribe(response => {
        console.log(response);
        localStorage.removeItem('userid');
        localStorage.removeItem('username');
        this.route.navigateByUrl('/welcome/login');
      });
  }
}
