import {Component, OnInit} from '@angular/core';
import {PatchGroupModel} from '../../../../share/model/group-info.model';
import {GroupUser} from '../../../../share/model/user-info.model';
import {ActivatedRoute, Router} from '@angular/router';
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
  origin: PatchGroupModel = new PatchGroupModel();
  patched: PatchGroupModel = new PatchGroupModel();
  gid: number;
  expandSet = new Set<number>();
  mTable: TableParamsModel<GroupUser> = new TableParamsModel(true, 6, 1);
  nTable: TableParamsModel<NotificationInfoModel> = new TableParamsModel(true, 6, 1);
  visible = false;
  editable = false;
  permissions = [
    {
      value: 0,
      description: '需要管理员审核',
    },
    {
      value: 1,
      description: '允许任何人加入',
    },
    {
      value: 2,
      description: '不允许任何人加入'
    }
  ]

  constructor(private route: ActivatedRoute,
              private service: PublishCenterService,
              private modal: NzModalService,
              private router: Router) {
  }

  loadNotificationData(pageIndex: number, pageSize: number): void {
    this.nTable.loading = true;
    this.service.getGroupNotifications(this.gid, 'creationDate desc', pageIndex, pageSize).subscribe(
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
    this.gid = toNumber(this.route.snapshot.paramMap.get('id'));
    this.getGroupInfo(this.gid);
    this.loadDataFromServer(this.mTable.pageIndex, this.mTable.pageSize);
    this.loadNotificationData(this.nTable.pageIndex, this.nTable.pageSize);
  }

  getGroupInfo(id: number) {
    this.service.getGroupInfo(id).subscribe(
      data => {
        this.origin.setValue(data.title, data.remark, data.permission);
        this.patched.setValue(data.title, data.remark, data.permission);
        console.log(data);
      },
      (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    )
  }

  loadDataFromServer(pageIndex: number, pageSize: number): void {
    this.mTable.loading = true;
    this.service.getGroupUser(this.gid, 'id', pageIndex, pageSize).subscribe(
      (data: ResponseModel<GroupUser>) => {
        this.mTable.setTable(data.list, data.total, false);
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    );
  }

  onQueryParamsChange(params: NzTableQueryParams): void {
    const {pageSize, pageIndex} = params;
    this.loadDataFromServer(pageIndex, pageSize);
  }

  // 提交编辑群组信息的表单
  submitForm(): void {
    // 如果没有更改表单内容就点击了保存，不会提交表单。
    if (this.origin.equals(this.patched)) {
      this.editable = false;
    } else {
      this.service.updateGroupInfo(this.gid, this.patched).subscribe(
        () => {
          this.service.msg.success('编辑成功');
          this.editable = false;
          this.origin.copy(this.patched);
        }, (error: HttpErrorResponse) => {
          this.service.msg.error(error.error.message);
        }
      );
    }
  }

  // 设置或撤销管理员
  setAdmin(uid: number, isAdmin: number) {
    if (isAdmin === 0) {
      isAdmin = 1;
    } else {
      isAdmin = 0;
    }
    this.service.updateUserPermission(this.gid, uid, isAdmin).subscribe(
      () => {
        this.service.msg.success('操作成功');
        setTimeout(() => {
          // 添加一个延时，防止新数据还未被写入时就读取了旧数据
          this.loadDataFromServer(this.mTable.pageIndex, this.mTable.pageSize);
        }, 500);
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    );
  }

  deleteUser(uid: number) {
    this.service.removeGroupUser(this.gid, uid).subscribe(
      () => {
        this.service.msg.success('操作成功');
        this.loadDataFromServer(this.mTable.pageIndex, this.mTable.pageSize);
      },
      (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    )
  }

  dismissGroup() {
    this.modal.confirm({
      nzTitle: '您确定要解散这个群组吗?',
      nzOkText: '确定',
      nzOkType: 'danger',
      nzOnOk: () => {
        this.service.dismissGroup(this.gid).subscribe(
          () => {
            this.router.navigateByUrl('/notification/publish').then(
              () => {
                this.service.msg.success('您已解散该群');
              });
          }, (error: HttpErrorResponse) => {
            this.service.msg.error(error.error.message);
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
        gid: this.gid
      },
      nzOnOk: () => {
        console.log('调用了nzOnOk')
        modalRef.getContentComponent().submitFrom();
        setTimeout(() => {
          this.loadNotificationData(this.nTable.pageIndex, this.nTable.pageSize);
        }, 500);
      }, nzOnCancel: () => {
        console.log('调用了nzOnCancel')
      }
    });
  }

  // 删除一条通知
  deleteNotification(nid: number) {
    this.service.deleteNotification(this.gid, nid).subscribe(
      () => {
        this.loadNotificationData(this.nTable.pageIndex, this.nTable.pageSize);
        this.service.msg.success('删除成功');
      }, (error: HttpErrorResponse) => {
        this.service.msg.error(error.error.message);
      }
    )
  }
}
