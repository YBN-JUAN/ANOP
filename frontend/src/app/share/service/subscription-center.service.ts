import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {GroupInfo} from '../model/group-info';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionCenterService {
  public url:string = 'http://localhost:8080/v1/sub/groups';
  getGroups(
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
