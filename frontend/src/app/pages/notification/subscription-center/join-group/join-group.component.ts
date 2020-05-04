import {Component, OnInit} from '@angular/core';
import {JoinGroupService} from './join-group.service';
import {HttpErrorResponse} from '@angular/common/http';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {PublishCenterService} from '../../../../share/service/publish-center.service';

@Component({
  selector: 'app-join-group',
  templateUrl: './join-group.component.html',
  styleUrls: ['./join-group.component.css'],
  providers: [JoinGroupService]
})
export class JoinGroupComponent implements OnInit {

  public groupId: number;
  public group: GroupInfoModel;
  public permissionDescribe: string;

  constructor(private joinGroupService: JoinGroupService, private searchGroupService: PublishCenterService) {
  }

  ngOnInit(): void {
    this.group = new GroupInfoModel();
  }

  doSearch() {
    // alert('搜索群组' + this.groupId);
    this.searchGroupService.getGroup(this.groupId).subscribe(
      (data: GroupInfoModel) => {
        console.log(data);
        this.group = data;
        this.setPermissionDescribe();
      }, (response: HttpErrorResponse) => {
        alert(response.error.status + ':' + response.error.message);
        this.group = new GroupInfoModel();
      }
    )
  }

  doJoin() {
    // alert("加入群组" + this.groupId);
    if (this.groupId == null) {
      alert('请输入群组ID');
      return;
    }
    this.joinGroupService.joinGroup(this.groupId).subscribe(
      () => {
        alert('申请加群成功，请等待管理员审核。');
      }, (response: HttpErrorResponse) => {
        alert(response.error.status + ':' + response.error.message);
      });
  }

  doReset() {
    this.group = new GroupInfoModel();
    this.permissionDescribe = '';
  }

  setPermissionDescribe(){
    switch (this.group.permission) {
      case 0:
        this.permissionDescribe = '需要管理员审核';
        break
      case 1:
        this.permissionDescribe = '允许任何人加入'
        break
      case 2:
        this.permissionDescribe = '不允许任何人加入'
        break
      default:
        this.permissionDescribe = '未知'
        break
    }
  }
}
