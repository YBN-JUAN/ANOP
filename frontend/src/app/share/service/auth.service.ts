import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {JsonResult} from '../model/json-result';
import {finalize} from 'rxjs/operators';
import {Router} from '@angular/router';
import {ApiUrlResource} from '../resource/api-url.resource';

interface User {
  id: number;
  username: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user: User;
  private url = ApiUrlResource.LOGIN;
  authenticated = false;

  constructor(
    private http: HttpClient,
    private route: Router
  ) {
  }

  authenticate<T>(credentials: { username: string; password: string; },
                  successCallback?: () => void,
                  errorCallback?: (result: JsonResult<T>) => void) {
    if (!credentials)
      return;
    this.http.get(this.url).pipe(
      finalize(() => {
        const formData = new URLSearchParams();
        formData.set('username', credentials.username);
        formData.set('password', credentials.password);
        this.http.post<User>(this.url, formData.toString(), {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }).subscribe(response => {
            this.authenticated = true;
            this.user = response;
            console.log(this.user);
            return successCallback && successCallback();
          },
          (errorResponse: HttpErrorResponse) => {
            this.route.navigateByUrl('/welcome/login').then(r => {
              console.log(r);
            });
            this.authenticated = false;
            console.log(errorResponse);
            return errorCallback && errorCallback(errorResponse.error);
          });
      })
    ).subscribe();
  }
}
