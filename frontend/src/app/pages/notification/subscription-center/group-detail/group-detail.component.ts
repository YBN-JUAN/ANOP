import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {SubscriptionCenterService} from '../../../../share/service/subscription-center.service';
import {NzModalService, NzTableQueryParams, toNumber} from 'ng-zorro-antd';
import {GroupUser} from '../../../../share/model/user-info.model';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {NotificationInfoModel} from '../../../../share/model/notification-info.model';
import {TableParamsModel} from '../../../../share/model/table-params.model';

@Component({
  selector: 'app-group-detail',
  templateUrl: './group-detail.component.html',
  styleUrls: ['./group-detail.component.css']
})

export class GroupDetailComponent implements OnInit {

  public group: GroupInfoModel;
  total = 1;
  listOfUsers: GroupUser[] = [];
  listOfNotifications: NotificationInfoModel[] = [];
  loading = true;
  pageSize = 3;
  pageIndex = 1;
  visible = false;
  nTable: TableParamsModel<NotificationInfoModel> = new TableParamsModel(true, 6, 1);
  expandSet = new Set<number>();

  constructor(private route: ActivatedRoute,
              private pubService: PublishCenterService,
              private subService: SubscriptionCenterService,
              private modal: NzModalService) {
  }

  ngOnInit(): void {
    this.group = new GroupInfoModel();
    const id = toNumber(this.route.snapshot.paramMap.get('id'));
    this.group.remark = '';
    this.group.id = id;
    this.group.title = '...';
    this.group.creationDate = '...';
    this.group.avatarUrl = '...';
    this.getGroupInfo(id);
    this.loadDataFromServer(this.pageIndex, this.pageSize);
    this.getNotifications(id);
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
        console.log(this.group);
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

  getNotifications(gid) {
    const orderBy = 'creationDate desc'
    this.subService.getGroupMessage(gid, orderBy, this.nTable.pageIndex, this.nTable.pageSize).subscribe(
      data => {
        this.nTable.total = data.total;
        this.nTable.data = data.list;
        this.nTable.loading = false;
        console.log(data.list)
      }, error => {
        console.log(error);
      }
    )
  }

  loadDataFromServer(pageIndex: number, pageSize: number): void {
    this.loading = true;
    this.pubService.getGroupUser(this.group.id, 'nickname', pageIndex, pageSize).subscribe(data => {
        this.loading = false;
        this.total = data.total;
        this.listOfUsers = data.list;
        console.log(this.listOfUsers);
        console.log(data);
      },
      error => {
        console.log(error);
      }
    );
  }

  onQueryParamsChange(params: NzTableQueryParams): void {
    console.log(params);
    const {pageSize, pageIndex} = params;
    this.loadDataFromServer(pageIndex, pageSize);
  }

  openDrawer(): void {
    this.visible = true;
  }

  closeDrawer(): void {
    this.visible = false;
  }
}
