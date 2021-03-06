import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiUrlResource} from '../../../../share/resource/api-url.resource';

@Injectable({
  providedIn: 'root'
})
export class CreateGroupService {

  constructor(private http: HttpClient) {
  }

  createGroupPost(title, remark, permission) {
    const url = ApiUrlResource.PUB_GROUPS;
    return this.http.post(`${url}`, {title, remark, permission});
  }
}
