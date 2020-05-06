import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiUrlResource} from '../../../../share/resource/api-url.resource';

@Injectable({
  providedIn: 'root'
})
export class GroupMessageService {

  private url = ApiUrlResource.subscribeGroupUrl;
  private suffix = 'notifications';

  constructor(private http: HttpClient) {
  }

  getGroupMessage(gid: number): Observable<any> {
    return this.http.get(`${this.url}/${gid}/${this.suffix}`);
  }

  getSubscribeGroups(): Observable<any> {
    return this.http.get(`${this.url}`);
  }
}
