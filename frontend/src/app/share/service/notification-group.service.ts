import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ResponseModel} from '../model/response.model';
import {NotificationInfoModel} from '../model/notification-info.model';
import {GroupInfoModel} from '../model/group-info.model';

@Injectable({
  providedIn: 'root'
})
export abstract class NotificationGroupService {
  protected url: string;

  protected constructor(protected http: HttpClient) {
  }

  protected setUrl(value: string) {
    this.url = value;
  }

  // 获取通知列表
  protected getGroupNotifications(gid: number, orderBy: string, pageNum: number, pageSize: number):
    Observable<ResponseModel<NotificationInfoModel>> {
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<NotificationInfoModel>>(`${this.url}/${gid}/notifications`, {params});
  }

  // 获取群组列表
  protected getGroups(orderBy: string, pageNum: number, pageSize: number, option?: string) {
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<GroupInfoModel>>(`${this.url}/${option}`, {params});
  }

  // 解散群组（发布者），退出群（订阅者）
  protected deleteGroup(id: number) {
    return this.http.delete(`${this.url}/${id}`);
  }
}
