import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JoinGroupService {
  public url = 'http://localhost:8080/v1/pub/requests';

  constructor(private http: HttpClient) {
  }

  joinGroup(gid: number): Observable<any> {
    return this.http.post(`${this.url}`, {gid});
  }
}
