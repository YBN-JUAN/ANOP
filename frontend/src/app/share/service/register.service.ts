import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Email, RegisterInfo} from '../model/register-info.model';
import {ApiUrlResource} from '../resource/api-url.resource';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private signUpUrl = ApiUrlResource.SIGN_UP;
  private validateUrl = ApiUrlResource.VALIDATE_EMAIL;

  constructor(private http: HttpClient) {
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  sendCode(email: Email) {
    return this.http.post(this.validateUrl, email, this.httpOptions);
  }

  register(info: RegisterInfo) {
    return this.http.post(this.signUpUrl, info, this.httpOptions);
  }
}
