import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JoinGroupService {
  public url: string = 'http://localhost:8080/v1/pub/requests';

  constructor(private http: HttpClient) {
  }

  joinGroup(groupId: string): Observable<any> {
    return this.http.post(`${this.url}`, {groupId: groupId});
  }
}
