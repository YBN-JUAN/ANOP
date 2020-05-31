import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ResponseModel} from '../model/response.model';
import {GroupInfoModel} from '../model/group-info.model';
import {ApiUrlResource} from '../resource/api-url.resource';
import {Observable} from 'rxjs';
import {NotificationInfoModel} from '../model/notification-info.model';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionCenterService {
  private url = ApiUrlResource.SUB_GROUPS;
  private suffix = 'notifications';

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
    return this.http.delete(`${this.url}/${id}`);
  }

  getGroupNotifications(gid: number, orderBy: string, pageNum: number, pageSize: number):
    Observable<ResponseModel<NotificationInfoModel>> {
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<NotificationInfoModel>>(`${this.url}/${gid}/${this.suffix}`, {params});
  }

  setIsRead(gid: number, nid: number) {
    return this.http.post(`${this.url}/${gid}/notifications/${nid}/readers`, {});
  }

  getAuto(gid: number) {
    return this.http.get<{ isAuto }>(`${this.url}/${gid}/autoTodo`);
  }

  setAuto(gid: number, isAuto: number) {
    return this.http.patch(`${this.url}/${gid}/autoTodo`, {isAuto});
  }
}
