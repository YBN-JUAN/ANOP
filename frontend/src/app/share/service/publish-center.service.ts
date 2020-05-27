import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ResponseModel} from '../model/response.model';
import {GroupInfoModel} from '../model/group-info.model';
import {GroupUser} from '../model/user-info.model';
import {ApiUrlResource} from '../resource/api-url.resource';

@Injectable({
  providedIn: 'root'
})
export class PublishCenterService {
  private url = ApiUrlResource.publishGroupUrl;

  getCreateGroups(orderBy: string, pageNum: number, pageSize: number) {
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<GroupInfoModel>>(`${this.url}`, {params});
  }

  getManageGroups(orderBy: string, pageNum: number, pageSize: number) {
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<GroupInfoModel>>(`${this.url}/manage`, {params});
  }

  getGroups(listType: number, orderBy: string, pageNum: number, pageSize: number) {
    if (listType === 0) {
      return this.getCreateGroups(orderBy, pageNum, pageSize);
    } else {
      return this.getManageGroups(orderBy, pageNum, pageSize);
    }
  }

  dismissGroup(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe(
      data => {
        console.log('delete group ok', data);
      },
      error => {
        console.log('delete group fail', error);
      }
    );
  }

  getGroup(id: number) {
    return this.http.get<GroupInfoModel>(`${this.url}/${id}`);
  }

  getGroupUser(groupId: number, orderBy: string, pageNum: number, pageSize: number) {
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<GroupUser>>(`${this.url}/${groupId}/users`, {params});
  }

  constructor(private http: HttpClient) {
  }

}
