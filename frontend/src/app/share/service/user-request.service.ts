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

  getCreateRequest(
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


}
