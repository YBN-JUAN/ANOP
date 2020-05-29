import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Email, RegisterInfo} from '../model/register-info.model';
import {ApiUrlResource} from '../resource/api-url.resource';
import {JsonResult} from '../model/json-result';
import {finalize} from 'rxjs/operators';

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

  register<T>(info: RegisterInfo, successCallback?: () => void,
                  errorCallback?: (result: JsonResult<T>) => void) {
    if (!info)
      return;
    this.http.options(this.signUpUrl).pipe(
      finalize(() => {
        this.http.post<RegisterInfo>(this.signUpUrl, info, this.httpOptions)
          .subscribe(response => {
            console.log("register ok");
            return successCallback && successCallback();
          },
          (errorResponse: HttpErrorResponse) => {
            console.log(errorResponse);
            return errorCallback && errorCallback(errorResponse.error);
          });
      })
    ).subscribe();
  }
}
