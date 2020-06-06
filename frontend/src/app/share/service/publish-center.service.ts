import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ResponseModel} from '../model/response.model';
import {GroupInfoModel, PatchGroupModel} from '../model/group-info.model';
import {GroupUser} from '../model/user-info.model';
import {ApiUrlResource} from '../resource/api-url.resource';
import {NotificationGroupService} from './notification-group.service';
import {NzMessageService} from 'ng-zorro-antd';
import {NotificationModel} from '../model/notification.model';
import {Observable} from 'rxjs';
import {NotificationInfoModel} from '../model/notification-info.model';

@Injectable({
  providedIn: 'root'
})
export class PublishCenterService extends NotificationGroupService {
  protected url = ApiUrlResource.PUB_GROUPS;

  constructor(protected http: HttpClient, public msg: NzMessageService) {
    super(http);
    super.setUrl(this.url);
  }

  getGroupsByType(listType: number, orderBy: string, pageNum: number, pageSize: number) {
    if (listType === 0) {
      return this.getGroups(orderBy, pageNum, pageSize, '');
    } else {
      return this.getGroups(orderBy, pageNum, pageSize, 'manage');
    }
  }

  updateGroupInfo(id: number, body: PatchGroupModel) {
    return this.http.patch(`${this.url}/${id}`, body);
  }

  dismissGroup(id: number) {
    return super.deleteGroup(id);
  }

  getGroupInfo(id: number) {
    return this.http.get<GroupInfoModel>(`${this.url}/${id}`);
  }

  getGroupUser(groupId: number, orderBy: string, pageNum: number, pageSize: number) {
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get<ResponseModel<GroupUser>>(`${this.url}/${groupId}/users`, {params});
  }

  updateUserPermission(gid: number, uid: number, isAdmin: number) {
    return this.http.patch(`${this.url}/${gid}/users/${uid}`, {isAdmin});
  }

  removeGroupUser(gid: number, uid: number) {
    return this.http.delete(`${this.url}/${gid}/users/${uid}`);
  }

  getGroupNotifications(gid: number, orderBy: string, pageNum: number, pageSize: number): Observable<ResponseModel<NotificationInfoModel>> {
    return super.getGroupNotifications(gid, orderBy, pageNum, pageSize);
  }

  asTodo(gid: number, nid: number) {
    return this.http.post(`${this.url}/${gid}/notifications/${nid}/asTodo`, {});
  }

  deleteNotification(gid: number, nid: number) {
    return this.http.delete(`${this.url}/${gid}/notifications/${nid}`, {});
  }

  updateNotification(nid: number, model: NotificationModel) {
    return this.http.patch(`${this.url}/${model.gid}/notifications/${nid}`, {
      title: model.title,
      content: model.content
    });
  }

  getNotificationReaders(gid: number, nid: number): Observable<GroupUser[]> {
    return this.http.get<GroupUser[]>(`${this.url}/${gid}/notifications/${nid}/readers`, {});
  }
}
