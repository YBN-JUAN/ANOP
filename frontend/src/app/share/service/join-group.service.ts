import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JoinGroupService {
  public url: string = "http://localhost:8080/v1/pub/requests";

  constructor() {
  }
}
