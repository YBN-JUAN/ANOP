import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';
import {ResponseModel} from '../model/response.model';
import {GroupInfoModel, GroupUpdateInfo, UpdateUserInfo} from '../model/group-info.model';
import {GroupUser} from '../model/user-info.model';
import {ApiUrlResource} from '../resource/api-url.resource';
import {JsonResult} from '../model/json-result';
import {finalize} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PublishCenterService {
  private url = ApiUrlResource.PUB_GROUPS;

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

  updateGroup<T>(id: number, info: GroupUpdateInfo,
                  successCallback?: () => void,
                  errorCallback?: (result: JsonResult<T>) => void) {
    if (!info)
      return;
    this.http.options(`${this.url}/${id}`).pipe(
      finalize(() => {
        this.http.patch<GroupUpdateInfo>(`${this.url}/${id}`, info)
          .subscribe(response => {
            return successCallback && successCallback();
          },
          (errorResponse: HttpErrorResponse) => {
            return errorCallback && errorCallback(errorResponse.error);
          });
      })
    ).subscribe();
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

  updateGroupUser(gid: number, uid: number, info: UpdateUserInfo) {
    if (!info)
      return;
    return this.http.patch<UpdateUserInfo>(`${this.url}/${gid}/users/${uid}`, info);
  }


  delGroupUser(gid: number, uid: number) {
    return this.http.delete(`${this.url}/${gid}/users/${uid}`);
  }

  constructor(private http: HttpClient) {
  }

}
