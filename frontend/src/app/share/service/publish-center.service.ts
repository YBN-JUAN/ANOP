import { Injectable } from '@angular/core';
import {GroupInfo} from '../model/group-info';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PublishCenterService {
  public url:string = 'http://localhost:8080/v1/pub/groups';
  getUsers(
    orderBy: string,
    pageNum: number,
    pageSize: number
  ) {
    let params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<GroupInfo>(`${this.url}`, { params });
  }
  constructor(private http: HttpClient) { }

}
