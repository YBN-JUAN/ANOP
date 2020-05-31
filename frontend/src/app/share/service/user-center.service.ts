import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserInfoModel} from '../model/user-info.model';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {ApiUrlResource} from '../resource/api-url.resource';
import {NzMessageService} from 'ng-zorro-antd';

@Injectable({
  providedIn: 'root'
})
export class UserCenterService {
  public user: UserInfoModel;
  private profileUrl = ApiUrlResource.PROFILE;
  private passwordUrl = ApiUrlResource.PASSWORD;
  private logoutURL = ApiUrlResource.LOGOUT;
  public AvatarURL = ApiUrlResource.AVATAR;
  storageOk = false;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  avatarHttpOptions = {
    headers: new HttpHeaders({
      'Content-Type': undefined
    })
  };

  constructor(private http: HttpClient, private route: Router, private msg: NzMessageService) {
  }

  getConfig(): Observable<UserInfoModel> {
    return this.http.get<UserInfoModel>(this.profileUrl);
  }

  storageUser() {
    if (this.storageOk) {
      return;
    }
    this.http.get<UserInfoModel>(this.profileUrl).subscribe(
      data => {
        localStorage.setItem('userid', String(data.id))
        localStorage.setItem('username', String(data.userName))
      })
    this.storageOk = true;
  }

  updateUserInfo(nickName: string, avatarUrl: string) {
    return this.http.put(this.profileUrl, {nickname: nickName, avatarUrl}, this.httpOptions);
  }

  resetPassword(newPassword: string, oldPassword: string) {
    return this.http.post(this.passwordUrl, {newPassword, oldPassword}, this.httpOptions);
  }

  uploadAvatar(formData: FormData) {
    return this.http.post(this.AvatarURL, formData, this.avatarHttpOptions);
  }

  signOut() {
    this.http.post(this.logoutURL, {}).subscribe(
      response => {
        console.log(response);
        localStorage.removeItem('userid');
        localStorage.removeItem('username');
      });
    this.route.navigateByUrl('/welcome/login').then(
      () => {
        this.msg.success('登出成功');
        console.log('登出');
      });
  }
}
