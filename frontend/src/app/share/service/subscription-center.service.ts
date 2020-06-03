import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ResponseModel} from '../model/response.model';
import {ApiUrlResource} from '../resource/api-url.resource';
import {Observable} from 'rxjs';
import {NotificationInfoModel} from '../model/notification-info.model';
import {NotificationGroupService} from './notification-group.service';
import {NzMessageService} from 'ng-zorro-antd';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionCenterService extends NotificationGroupService {
  protected readonly url = ApiUrlResource.SUB_GROUPS;

  constructor(protected http: HttpClient, public msg: NzMessageService) {
    super(http);
    super.setUrl(this.url);
  }

  getGroups(orderBy: string, pageNum: number, pageSize: number) {
    return super.getGroups(orderBy, pageNum, pageSize, '');
  }

  quitGroup(id: number) {
    return super.deleteGroup(id);
  }

  getGroupNotifications(gid: number, orderBy: string, pageNum: number, pageSize: number):
    Observable<ResponseModel<NotificationInfoModel>> {
    return super.getGroupNotifications(gid, orderBy, pageNum, pageSize);
  }

  setIsRead(gid: number, nid: number) {
    return this.http.post(`${this.url}/${gid}/notifications/${nid}/readers`, {});
  }

  getAuto(gid: number) {
    return this.http.get<{ isAuto: number }>(`${this.url}/${gid}/autoTodo`);
  }

  setAuto(gid: number, isAuto: number) {
    return this.http.patch(`${this.url}/${gid}/autoTodo`, {isAuto});
  }

  getUnreadCount(id: number) {
    return this.http.get<{ groupId: number, unreadCount: number }>(`${this.url}/${id}/notifications/unread_count`, {});
  }
}
