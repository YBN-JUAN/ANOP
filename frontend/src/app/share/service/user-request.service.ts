import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpParams} from '@angular/common/http';
import {UserRequest} from '../model/user-request';
import {ResponseModel} from '../model/response.model';
import {catchError} from 'rxjs/operators';
import {throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserRequestService {
  public url: string = "http://localhost:8080/v1/pub/requests";
  constructor(private http: HttpClient) { }

  getCreateRequest(
    orderBy: string,
    pageNum: number,
    pageSize: number
  ) {
    let params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<UserRequest>>(`${this.url}`, { params });
  }

  getManageRequest(
    orderBy: string,
    pageNum: number,
    pageSize: number
  ) {
    let params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<UserRequest>>(`${this.url}/manage`, { params });
  }

  getUserRequest(
    listType: number,
    orderBy: string,
    pageNum: number,
    pageSize: number
  ) {
    if (listType == 0) {
      return this.getCreateRequest(orderBy, pageNum, pageSize);
    } else {
      return this.getManageRequest(orderBy, pageNum, pageSize);
    }
  }

  dealRequest(id: number, isAccepted: number) {
    let body = { isAccepted };
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    return this.http.post(`${this.url}/${id}`, body, httpOptions)
      .pipe(
        catchError(this.handleError)
      )
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
}
