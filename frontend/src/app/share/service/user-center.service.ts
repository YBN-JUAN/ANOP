import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserInfoModel} from '../model/user-info.model';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {ApiUrlResource} from '../resource/api-url.resource';

@Injectable({
  providedIn: 'root'
})
export class UserCenterService {
  public user: UserInfoModel;
  public ProfileURL = ApiUrlResource.PROFILE;
  public PasswordURL = ApiUrlResource.PASSWORD;
  public LogoutURL = ApiUrlResource.LOGOUT;
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

  constructor(
    private http: HttpClient,
    private route: Router
  ) {
  }

  getConfig(): Observable<UserInfoModel> {
    return this.http.get<UserInfoModel>(this.ProfileURL);
  }

  storageUser() {
    if (this.storageOk) {
      return;
    }
    this.http.get<UserInfoModel>(this.ProfileURL).subscribe(data => (
      localStorage.setItem('userid', String(data.id)),
        localStorage.setItem('username', String(data.userName))
    ))
    this.storageOk = true;
  }

  updateUserInfo(nickName: string, avatarUrl: string) {
    return this.http.put(this.ProfileURL, {nickname: nickName, avatarUrl}, this.httpOptions);
  }

  resetPassword(newPassword: string, oldPassword: string) {
    return this.http.post(this.PasswordURL, { newPassword, oldPassword }, this.httpOptions);
  }

  uploadAvatar(formData: FormData) {
    return this.http.post(this.AvatarURL, formData, this.avatarHttpOptions);
  }

  signOut() {
    this.http.post(this.LogoutURL, {}, this.httpOptions)
      .subscribe(response => {
        console.log(response);
        localStorage.removeItem('userid');
        localStorage.removeItem('username');
      });
    this.route.navigateByUrl('/welcome/login');
  }
}
