import {Component, OnInit} from '@angular/core';
import {JoinGroupService} from '../../../../share/service/join-group.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-join-group',
  templateUrl: './join-group.component.html',
  styleUrls: ['./join-group.component.css'],
  providers: [JoinGroupService]
})
export class JoinGroupComponent implements OnInit {

  public groupId = '';


  constructor(private service: JoinGroupService) {
  }

  ngOnInit(): void {
  }

  doSearch() {
    alert('搜索群组' + this.groupId);
  }

  doJoin() {
    // alert("加入群组" + this.groupId);
    if (this.groupId == null) {
      alert('null groupId');
      return;
    }
    this.service.joinGroup(this.groupId).subscribe(
      () => {
        alert('申请加群成功，请等待管理员审核。');
      }, (response: HttpErrorResponse) => {
        alert(response.error.status + ':ng l' + response.error.message);
      });
  }
}
