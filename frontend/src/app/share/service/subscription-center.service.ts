import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ResposeList} from '../model/respose-list';
import {Group} from '../model/group-info';
import {GroupUser} from '../model/user-info';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionCenterService {
  public url:string = 'http://localhost:8080/v1/sub/groups';
  public url1:string = 'http://localhost:8080/v1/pub/groups';

  getGroups(
    orderBy: string,
    pageNum: number,
    pageSize: number
  ) {
    let params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResposeList<Group>>(`${this.url}`, { params });
  }

  getGroup(id: number) {
    return this.http.get<Group>(`${this.url1}/${id}`);
  }

  getGroupUser(
    groupId: number,
    orderBy: string,
    pageNum: number,
    pageSize: number
  ) {
    let params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResposeList<GroupUser>>(`${this.url1}/${groupId}/users`, {params});
  }

  constructor(private http: HttpClient) { }
}
