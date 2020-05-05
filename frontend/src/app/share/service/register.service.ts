import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Email, RegisterInfoModel} from '../model/register-info.model';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http:HttpClient) { }
  private baseUrl:string = 'http://localhost:8080/v1/';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  sendCode(email: Email) {
    return this.http.post(this.baseUrl + "valid_email", email, this.httpOptions);
  }

  register(info: RegisterInfoModel) {
    return this.http.post(this.baseUrl + "signup", info, this.httpOptions);
  }
}
