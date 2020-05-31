import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ResponseModel} from '../model/response.model';
import {GroupInfoModel} from '../model/group-info.model';
import {ApiUrlResource} from '../resource/api-url.resource';
import {Observable} from 'rxjs';

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
    this.http.delete(`${this.url}/${id}`).subscribe(
      data => {
        console.log('quit group ok', data);
      },
      error => {
        console.log('quit group fail', error);
      }
    )
  }

  getGroupMessage(gid: number, orderBy: string, pageNum: number, pageSize: number): Observable<any> {
    const params = new HttpParams()
      .append('orderBy', orderBy)
      .append('pageNum', `${pageNum}`)
      .append('pageSize', `${pageSize}`);
    return this.http.get(`${this.url}/${gid}/${this.suffix}`, {params});
  }

  setIsRead(gid: number, nid: number) {
    return this.http.post(`${this.url}/${gid}/notifications/${nid}/readers`, {});
  }

  setAuto(gid: number, isAuto: number) {
    return this.http.patch(`${this.url}/${gid}/autoTodo`,{ 'isAuto': isAuto });
  }
}
