import {Component, OnInit} from '@angular/core';
import {JoinGroupService} from "../../../../share/service/join-group.service";

@Component({
  selector: 'app-join-group',
  templateUrl: './join-group.component.html',
  styleUrls: ['./join-group.component.css'],
  providers: [JoinGroupService]
})
export class JoinGroupComponent implements OnInit {

  public groupId: string = '';
  private service: JoinGroupService;

  constructor() {
  }

  ngOnInit(): void {
  }

  doSearch() {
    alert("搜索群组" + this.groupId);
  }

  doJoin() {
    //alert("加入群组" + this.groupId);
    if (this.groupId == null) {
      alert("null groupId");
      return;
    }
    this.service.joinGroup(this.groupId).subscribe(
      data => {
        console.log(data);
      }, error => {
        console.error(error);
      })
  }
}
