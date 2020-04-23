import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserInfo } from '../model/user-info';

@Injectable({
  providedIn: 'root'
})

export class UserCenterService {
  public url:string = 'http://localhost:8080/v1/profile';
  storageOk: boolean = false;

  constructor(public http:HttpClient) { 
  }

  getConfig(){
    return this.http.get<UserInfo>(this.url);
  }

  storageUser() {
    if (this.storageOk) { return; }
    this.http.get<UserInfo>(this.url).subscribe(data => (
      localStorage.setItem("userid", String(data.id)),
        localStorage.setItem("username", String(data.userName))
    ))
    this.storageOk = true;
  }
}
