import {Component, OnInit} from '@angular/core';
import {JoinGroupService} from './join-group.service';
import {HttpErrorResponse} from '@angular/common/http';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-join-group',
  templateUrl: './join-group.component.html',
  styleUrls: ['./join-group.component.css'],
  providers: [JoinGroupService]
})
export class JoinGroupComponent implements OnInit {

  public groupId: number;
  group: GroupInfoModel;
  permissionDescribe = '';

  constructor(private joinGroupService: JoinGroupService,
              private searchGroupService: PublishCenterService,
              private msg: NzMessageService) {
  }

  ngOnInit(): void {
    this.group = new GroupInfoModel();
  }

  doSearch() {
    if (this.groupId == null) {
      this.msg.warning('请输入群组ID')
      return;
    }
    this.searchGroupService.getGroupInfo(this.groupId).subscribe(
      (data: GroupInfoModel) => {
        console.log(data);
        this.group = data;
        this.setPermissionDescribe();
      }, (response: HttpErrorResponse) => {
        this.msg.error(response.error.message);
        this.group = new GroupInfoModel();
        this.permissionDescribe = '';
      }
    )
  }

  doJoin() {
    this.doSearch();
    this.joinGroupService.joinGroup(this.groupId).subscribe(
      () => {
        if (this.group.permission === 0) {
          this.msg.info('请求发送成功，请等待管理员审核');
        } else {
          this.msg.success('已成功加入群\"' + this.group.title + '\"');
        }
      }, (response: HttpErrorResponse) => {
        this.msg.error(response.error.message);
      }
    );
  }

  doReset() {
    this.group = new GroupInfoModel();
    this.permissionDescribe = '';
    this.groupId = null;
  }

  setPermissionDescribe() {
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
        this.permissionDescribe = ''
        break
    }
  }
}
