import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { UserInfo } from '../model/user-info';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserCenterService {
  public url: string = 'http://localhost:8080/v1/profile';
  storageOk: boolean = false;

  constructor(public http:HttpClient) {
  }

  getConfig(){
    return this.http.get<UserInfo>(this.url);
  }

  storageUser() {
    if (this.storageOk) { return; }
    this.http.get<UserInfo>(this.url).subscribe(data => (
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
    this.http.put(this.url, {nickname:nickName}, httpOptions)
      .subscribe(response => {
        console.log(response);
      }
    );
  }
}
