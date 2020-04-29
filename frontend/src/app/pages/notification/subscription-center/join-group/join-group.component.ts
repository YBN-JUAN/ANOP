import {Component, OnInit} from '@angular/core';
import {JoinGroupService} from './join-group.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Group} from "../../../../share/model/group-info";
import {PublishCenterService} from "../../../../share/service/publish-center.service";

@Component({
  selector: 'app-join-group',
  templateUrl: './join-group.component.html',
  styleUrls: ['./join-group.component.css'],
  providers: [JoinGroupService]
})
export class JoinGroupComponent implements OnInit {

  public groupId: number;
  public group: Group = new Group();

  constructor(private joinGroupService: JoinGroupService, private searchGroupService: PublishCenterService) {
  }

  ngOnInit(): void {
  }

  doSearch() {
    alert('搜索群组' + this.groupId);
    this.searchGroupService.getGroup(this.groupId).subscribe(
      (data: Group) => {
        console.log(data);
        this.group = data;
      }
    )
  }

  doJoin() {
    // alert("加入群组" + this.groupId);
    if (this.groupId == null) {
      alert('null groupId');
      return;
    }
    this.joinGroupService.joinGroup(this.groupId).subscribe(
      () => {
        alert('申请加群成功，请等待管理员审核。');
      }, (response: HttpErrorResponse) => {
        alert(response.error.status + ':' + response.error.message);
      });
  }
}
