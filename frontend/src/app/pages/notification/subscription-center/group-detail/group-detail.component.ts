import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {SubscriptionCenterService} from '../../../../share/service/subscription-center.service';
import {NzMessageService, NzModalService, NzTableQueryParams, toNumber} from 'ng-zorro-antd';
import {GroupUser} from '../../../../share/model/user-info.model';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {NotificationInfoModel} from '../../../../share/model/notification-info.model';
import {TableParamsModel} from '../../../../share/model/table-params.model';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-group-detail',
  templateUrl: './group-detail.component.html',
  styleUrls: ['./group-detail.component.css'],
})

export class GroupDetailComponent implements OnInit {

  public group: GroupInfoModel;
  visible = false;
  mTable: TableParamsModel<GroupUser> = new TableParamsModel(true, 6, 1);
  nTable: TableParamsModel<NotificationInfoModel> = new TableParamsModel(true, 6, 1);
  expandSet = new Set<number>();
  isAuto = false;

  constructor(private activatedRoute: ActivatedRoute,
              private pubService: PublishCenterService,
              private subService: SubscriptionCenterService,
              private modal: NzModalService,
              private msg: NzMessageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.group = new GroupInfoModel();
    const id = toNumber(this.activatedRoute.snapshot.paramMap.get('id'));
    this.group.remark = '';
    this.group.id = id;
    this.group.title = '...';
    this.group.creationDate = '...';
    this.group.avatarUrl = '...';
    this.getGroupInfo(id);
    this.loadMemberDataFromServer(this.mTable.pageIndex, this.mTable.pageSize);
    this.loadNotificationDataFromServer(this.nTable.pageIndex, this.nTable.pageSize);
    this.getAuto();
  }

  onExpandChange(id: number, checked: boolean): void {
    if (checked) {
      this.expandSet.add(id);
    } else {
      this.expandSet.delete(id);
    }
  }

  getGroupInfo(id: number) {
    this.pubService.getGroup(id).subscribe(
      data => {
        this.group = data;
        console.log(data)
      },
      error => {
        console.log(error);
      }
    )
  }

  quitGroup() {
    this.modal.confirm({
      nzTitle: '您确定要退出这个群组吗?',
      nzOkText: '确定',
      nzOkType: 'danger',
      nzOnOk: () => {
        this.subService.quitGroup(this.group.id).subscribe(
          () => {
            this.router.navigateByUrl('/notification/subscription/group-list').then(
              () => {
                this.msg.success('您已退出群\"' + this.group.title + '\"');
              }
            )
          }, (error: HttpErrorResponse) => {
            this.msg.error(error.message);
          }
        );
      },
      nzCancelText: '取消',
      nzOnCancel: () => {
        console.log('Cancel')
      }
    });
  }

  loadMemberDataFromServer(pageIndex: number, pageSize: number): void {
    this.mTable.loading = true;
    this.pubService.getGroupUser(this.group.id, 'nickname', pageIndex, pageSize).subscribe(
      data => {
        this.mTable.loading = false;
        this.mTable.total = data.total;
        this.mTable.data = data.list;
        console.log(data);
      }, (response: HttpErrorResponse) => {
        this.msg.error(response.error.message)
        console.log(response);
      }
    );
  }

  loadNotificationDataFromServer(pageIndex: number, pageSize: number): void {
    this.nTable.loading = true;
    this.subService.getGroupNotifications(this.group.id, 'creationDate desc', pageIndex, pageSize).subscribe(
      data => {
        this.nTable.loading = false;
        this.nTable.total = data.total;
        this.nTable.data = data.list;
        console.log(this.nTable.data);
      },
      (response: HttpErrorResponse) => {
        this.msg.error(response.error.message)
        console.log(response);
      }
    );
  }

  onMemberQueryParamsChange(params: NzTableQueryParams): void {
    console.log(params);
    const {pageSize, pageIndex} = params;
    this.loadMemberDataFromServer(pageIndex, pageSize);
  }

  onNotificationQueryParamsChange(params: NzTableQueryParams): void {
    console.log(params);
    const {pageSize, pageIndex} = params;
    this.loadNotificationDataFromServer(pageIndex, pageSize);
  }

  openDrawer(): void {
    this.visible = true;
  }

  closeDrawer(): void {
    this.visible = false;
  }

  onReadStatusChange(isRead: number, nid: number) {
    this.subService.setIsRead(this.group.id, nid).subscribe(
      r => {
        console.log(r)
        this.msg.success('操作成功');
        location.reload();
      }, (response: HttpErrorResponse) => {
        this.msg.error(response.error.message);
      }
    );
  }

  getAuto() {
    this.subService.getAuto(this.group.id).subscribe(
      result => {
        console.log(result)
        this.isAuto = (result.isAuto === 1);
      }, (response: HttpErrorResponse) => {
        this.msg.error(response.error.message);
      }
    )
  }

  setAuto(isAuto: boolean) {
    this.subService.setAuto(this.group.id, isAuto === true ? 1 : 0).subscribe(
      () => {
        this.msg.success('修改成功', {nzDuration: 2000});
      }, (response: HttpErrorResponse) => {
        this.msg.error(response.error.message);
      }
    );
  }

  addTodo(nid: number) {
    this.pubService.asTodo(this.group.id, nid).subscribe(
      () => {
        this.msg.success('添加成功');
      }, (error: HttpErrorResponse) => {
        this.msg.error(error.error.message);
      }
    )
  }
}
