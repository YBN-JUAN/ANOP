import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ResposeList} from '../model/respose-list';
import {Group} from '../model/group-info';
import {GroupUser} from '../model/user-info';

@Injectable({
  providedIn: 'root'
})
export class PublishCenterService {
  public url:string = 'http://localhost:8080/v1/pub/groups';
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

  deleteGroup(id: number){
    this.http.delete(`${this.url}/${id}`).subscribe(
      data => {
        console.log("delete group ok", data);
      },
      error => {
        console.log("delete group fail", error);
      }
    )
  }

  getGroup(id: number) {
    return this.http.get<Group>(`${this.url}/${id}`);
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
    return this.http.get<ResposeList<GroupUser>>(`${this.url}/${groupId}/users`, {params});
  }
  constructor(private http: HttpClient) { }

}
