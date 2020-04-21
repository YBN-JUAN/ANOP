import {Component, OnInit} from '@angular/core';
import {UserCenterComponent} from "../../../user-center/user-center.component";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-join-group',
  templateUrl: './join-group.component.html',
  styleUrls: ['./join-group.component.css']
})
export class JoinGroupComponent implements OnInit {

  public groupId: string = "";

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
  }

  doSearch() {
    alert("搜索群组" + this.groupId);
  }

  doJoin() {
    alert("加入群组" + this.groupId);
  }
}
