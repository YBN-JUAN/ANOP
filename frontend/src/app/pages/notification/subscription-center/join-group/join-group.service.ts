import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiUrlResource} from '../../../../share/resource/api-url.resource';

@Injectable({
  providedIn: 'root'
})
export class JoinGroupService {
  private url = ApiUrlResource.PUB_REQUESTS;

  constructor(private http: HttpClient) {
  }

  joinGroup(groupId: number): Observable<any> {
    return this.http.post(`${this.url}`, {groupId});
  }
}
