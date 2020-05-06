import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ResponseModel} from '../model/response.model';
import {GroupInfoModel} from '../model/group-info.model';
import {ApiUrlResource} from '../resource/api-url.resource';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionCenterService {
  private url = ApiUrlResource.subscribeGroupUrl;

  constructor(private http: HttpClient) {
  }

  getGroups(orderBy: string, pageNum: number, pageSize: number) {
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<GroupInfoModel>>(`${this.url}`, {params});
  }

  quitGroup(id: number) {
    this.http.delete(`${this.url}/${id}`).subscribe(
      data => {
        console.log('delete group ok', data);
      },
      error => {
        console.log('delete group fail', error);
      }
    )
  }
}
