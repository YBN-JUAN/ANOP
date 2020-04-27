import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {UserRequest} from '../model/user-request';
import {ResposeList} from '../model/respose-list';

@Injectable({
  providedIn: 'root'
})
export class UserRequestService {
  public url: string = "http://localhost:8080/v1/pub/requests";
  constructor(private http: HttpClient) { }

  getUserRequest(
    orderBy: string,
    pageNum: number,
    pageSize: number
  ) {
    let params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResposeList<UserRequest>>(`${this.url}`, { params });
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
    return this.http.get<ResposeList<UserRequest>>(`${this.url}/manage`, { params });
  }
}
