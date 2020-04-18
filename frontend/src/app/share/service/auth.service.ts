import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { JsonResult } from '../model/json-result';
import { finalize } from 'rxjs/operators';

interface User{
  id:number;
  username:string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl:string='http://localhost:8080/';
  user:User;
  authenticated = false;
  constructor(private http: HttpClient) { }

  authenticate<T>(credentials, successCallback?:()=>void, errorCallback?:(result:JsonResult<T>)=>void) {
    if(!credentials)
      return;
    this.http.options(this.baseUrl+'login').pipe(
      finalize(()=>{
        let formData=new URLSearchParams();
        formData.set('username',credentials.username);
        formData.set('password',credentials.password);
        this.http.post<User>(this.baseUrl+'login',formData.toString(),{
          headers:{
            'Content-Type':'application/x-www-form-urlencoded'
          }
        }).subscribe(response => {
            this.authenticated = true;
            this.user=response;
            console.log(this.user);
            return successCallback && successCallback();
          },
          (errorResponse:HttpErrorResponse)=>{
            this.authenticated = false;
            console.log(errorResponse);
            return errorCallback && errorCallback(errorResponse.error);
          });
      })
    ).subscribe();
  }
}
