import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserInfo } from '../model/UserInfo';

@Injectable({
  providedIn: 'root'
})

export class UserCenterService {
  public url:string='http://localhost:8080/user';
  
  constructor(public http:HttpClient) { 
  }

  getConfig(){
    return this.http.get<UserInfo>(this.url);
  }
}
