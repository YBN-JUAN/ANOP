import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {SubscriptionCenterService} from '../../../../share/service/subscription-center.service';
import {NzMessageService, NzModalService, NzTableQueryParams, toNumber} from 'ng-zorro-antd';
import {GroupUser} from '../../../../share/model/user-info.model';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {NotificationInfoModel} from '../../../../share/model/notification-info.model';
import {TableParamsModel} from '../../../../share/model/table-params.model';
import {HttpErrorResponse} from '@angular/common/http';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-group-detail',
  templateUrl: './group-detail.component.html',
  styleUrls: ['./group-detail.component.css'],
  // changeDetection: ChangeDetectionStrategy.OnPush
})

export class GroupDetailComponent implements OnInit {

  public group: GroupInfoModel;
  total = 1;
  listOfUsers: GroupUser[] = [];
  loading = true;
  pageSize = 10;
  pageIndex = 1;
  visible = false;
  nTable: TableParamsModel<NotificationInfoModel> = new TableParamsModel(true, 6, 1);
  expandSet = new Set<number>();
  isAuto: false;

  // dataSource: MyDataSourceService;

  constructor(private route: ActivatedRoute,
              private pubService: PublishCenterService,
              private subService: SubscriptionCenterService,
              private modal: NzModalService,
              private msg: NzMessageService) {
  }

  ngOnInit(): void {
    this.group = new GroupInfoModel();
    const id = toNumber(this.route.snapshot.paramMap.get('id'));
    this.group.remark = '';
    this.group.id = id;
    this.group.title = '...';
    this.group.creationDate = '...';
    this.group.avatarUrl = '...';
    // this.dataSource = new MyDataSourceService(this.http, ApiUrlResource.PUB_GROUPS, this.group.id)
    this.getGroupInfo(id);
    this.loadMemberDataFromServer(this.pageIndex, this.pageSize);
    this.loadNotificationDataFromServer(this.nTable.pageIndex, this.nTable.pageSize);
    // console.log(this.dataSource)
    // this.getNotifications(id);
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
      nzTitle: '你确定要退出这个群组吗?',
      // nzContent: '<b style="color: red;">Some descriptions</b>',
      nzOkText: '确定',
      nzOkType: 'danger',
      nzOnOk: () => {
        this.subService.quitGroup(this.group.id);
        this.ngOnInit();
      },
      nzCancelText: '取消',
      nzOnCancel: () => console.log('Cancel')
    });
  }

  loadMemberDataFromServer(pageIndex: number, pageSize: number): void {
    this.loading = true;
    this.pubService.getGroupUser(this.group.id, 'nickname', pageIndex, pageSize).subscribe(
      data => {
        this.loading = false;
        this.total = data.total;
        this.listOfUsers = data.list;
        console.log(data);
      }, error => {
        console.log(error);
      }
    );
  }

  loadNotificationDataFromServer(pageIndex: number, pageSize: number): void {
    this.nTable.loading = true;
    this.subService.getGroupMessage(this.group.id, 'creationDate desc', pageIndex, pageSize).subscribe(
      data => {
        this.nTable.loading = false;
        this.nTable.total = data.total;
        this.nTable.data = data.list;
        console.log(this.nTable.data);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
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
    console.log(nid)
    // this.msg.success('操作成功');
    this.subService.setIsRead(this.group.id, nid).subscribe(
      r => {
        console.log(r)
        this.msg.success('操作成功');
        location.reload();
      }, (error: HttpErrorResponse) => {
        this.msg.error(error.message);
      }
    );
  }

  formattedDate(creationDate: string): string {
    return formatDate(creationDate, 'yyyy年MM月dd日 HH:mm', 'zh-CN');
  }

  setAuto(isAuto: boolean) {
    this.subService.setAuto(this.group.id, isAuto === true?1:0);
  }
}
