import {Component, OnInit} from '@angular/core';
import {GroupUpdateInfo, UpdateUserInfo} from '../../../../share/model/group-info.model';
import {GroupUser} from '../../../../share/model/user-info.model';
import {ActivatedRoute} from '@angular/router';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {NzModalService, NzTableQueryParams, toNumber} from 'ng-zorro-antd';
import {TableParamsModel} from '../../../../share/model/table-params.model';
import {NotificationInfoModel} from '../../../../share/model/notification-info.model';
import {HttpErrorResponse} from '@angular/common/http';
import {ResponseModel} from '../../../../share/model/response.model';
import {UpdateNotificationComponent} from '../update-notification/update-notification.component';

@Component({
  selector: 'app-manage-group',
  templateUrl: './manage-group.component.html',
  styleUrls: ['./manage-group.component.css']
})
export class ManageGroupComponent implements OnInit {

  updateGroupInfo: GroupUpdateInfo;
  groupId: number;
  permission: string;

  expandSet = new Set<number>();
  adminInfo: UpdateUserInfo;
  mTable: TableParamsModel<GroupUser> = new TableParamsModel(true, 6, 1);
  nTable: TableParamsModel<NotificationInfoModel> = new TableParamsModel(true, 6, 1);
  visible = false;

  constructor(private route: ActivatedRoute,
              private service: PublishCenterService,
              private modal: NzModalService) {
  }

  loadNotificationData(pageIndex: number, pageSize: number): void {
    this.nTable.loading = true;
    this.service.getGroupNotifications(this.groupId, 'creationDate desc', pageIndex, pageSize).subscribe(
      data => {
        this.nTable.setTable(data.list, data.total, false);
        console.log(this.nTable.data);
      },
      (response: HttpErrorResponse) => {
        this.service.msg.error(response.error.message)
        console.log(response);
      }
    );
  }

  ngOnInit(): void {
    this.updateGroupInfo = new GroupUpdateInfo();
    this.adminInfo = new UpdateUserInfo();
    const id = toNumber(this.route.snapshot.paramMap.get('id'));
    this.groupId = Number(id);
    this.updateGroupInfo.remark = '';
    this.updateGroupInfo.title = '...';
    this.getGroupInfo(id);
    this.loadDataFromServer(this.mTable.pageIndex, this.mTable.pageSize);
  }

  getGroupInfo(id: number) {
    this.service.getGroup(id).subscribe(
      data => {
        this.updateGroupInfo.permission = data.permission;
        this.updateGroupInfo.title = data.title;
        this.updateGroupInfo.remark = data.remark;
        this.permission = String(data.permission);
        console.log(data);
      },
      error => {
        console.log(error);
      }
    )
  }

  loadDataFromServer(pageIndex: number, pageSize: number): void {
    this.mTable.loading = true;
    this.service.getGroupUser(this.groupId, 'id', pageIndex, pageSize).subscribe(
      (data: ResponseModel<GroupUser>) => {
        this.mTable.setTable(data.list, data.total, false);
        console.log(data);
      },
      (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    );
  }

  onQueryParamsChange(params: NzTableQueryParams): void {
    console.log(params);
    const {pageSize, pageIndex} = params;
    this.loadDataFromServer(pageIndex, pageSize);
  }

  submitForm(): void {
    this.updateGroupInfo.permission = Number(this.permission);
    this.service.updateGroup(this.groupId, this.updateGroupInfo,
      () => {
        location.reload();
      }, (result) => {
        console.log(result.message);
      });
  }

  updateUser(info: GroupUser) {
    if (info.isAdmin === 0) {
      this.adminInfo.isAdmin = 1;
    } else {
      this.adminInfo.isAdmin = 0;
    }
    const uid = info.userId;
    this.service.updateGroupUser(this.groupId, uid, this.adminInfo).subscribe(
      () => {
        console.log('update user to admin ok');
        this.loadDataFromServer(this.mTable.pageIndex, this.mTable.pageSize);
      },
      error => {
        console.log(error);
      }
    );
  }

  deleteUser(uid: number) {
    this.service.removeGroupUser(this.groupId, uid).subscribe(
      () => {
        console.log('del user ok');
        this.loadDataFromServer(this.mTable.pageIndex, this.mTable.pageSize);
      },
      error => {
        console.log(error);
      }
    )
  }

  dismissGroup() {
    this.modal.confirm({
      nzTitle: '您确定要解散这个群组吗?',
      // nzContent: '<b style="color: red;">Some descriptions</b>',
      nzOkText: '确定',
      nzOkType: 'danger',
      nzOnOk: () => {
        this.service.dismissGroup(this.groupId).subscribe(
          () => {
            this.service.msg.success('您已解散该群');
          },
          error => {
            console.log('delete group fail', error);
          }
        );
        this.ngOnInit();
      },
      nzCancelText: '取消',
      nzOnCancel: () => console.log('Cancel')
    });
  }

  onNotificationQueryParamsChange(params: NzTableQueryParams): void {
    console.log(params);
    const {pageSize, pageIndex} = params;
    this.loadNotificationData(pageIndex, pageSize);
  }

  openDrawer(): void {
    this.visible = true;
  }

  closeDrawer(): void {
    this.visible = false;
  }

  onExpandChange(id: number, checked: boolean): void {
    if (checked) {
      this.expandSet.add(id);
    } else {
      this.expandSet.delete(id);
    }
  }

  editNotification(model: NotificationInfoModel) {
    const modalRef = this.modal.create({
      nzTitle: '编辑通知',
      nzContent: UpdateNotificationComponent,
      nzGetContainer: () => document.body,
      nzComponentParams: {
        service: this.service,
        title: model.title,
        content: model.content,
        nid: model.id,
        gid: this.groupId
      },
      nzOnOk: () => {
        console.log('调用了nzOnOk')
        modalRef.getContentComponent().submitFrom();
        this.loadNotificationData(this.nTable.pageIndex, this.nTable.pageSize);
      }, nzOnCancel: () => {
        console.log('调用了nzOnCancel')
      }
    });
  }

  deleteNotification(nid: number) {
    this.service.deleteNotification(this.groupId, nid).subscribe(
      () => {
        this.loadNotificationData(this.nTable.pageIndex, this.nTable.pageSize);
        this.service.msg.success('删除成功');
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    )
  }
}
