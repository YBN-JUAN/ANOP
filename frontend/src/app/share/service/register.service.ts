import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Email, RegisterInfo} from '../model/register-info';

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

  register(info: RegisterInfo) {
    return this.http.post(this.baseUrl + "signup", info, this.httpOptions);
  }
}
