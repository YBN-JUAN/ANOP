import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GroupMessageService {

  private url = 'http://localhost:8080/v1/sub/groups';
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
