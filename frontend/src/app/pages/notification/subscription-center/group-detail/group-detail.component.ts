import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {SubscriptionCenterService} from '../../../../share/service/subscription-center.service';
import {NzModalService, NzTableQueryParams, toNumber} from 'ng-zorro-antd';
import {GroupUser} from '../../../../share/model/user-info.model';
import {PublishCenterService} from '../../../../share/service/publish-center.service';

@Component({
  selector: 'app-group-detail',
  templateUrl: './group-detail.component.html',
  styleUrls: ['./group-detail.component.css']
})

export class GroupDetailComponent implements OnInit {
  public group: GroupInfoModel;
  total = 1;
  listOfUsers: GroupUser[] = [];
  loading = true;
  pageSize = 5;
  pageIndex = 1;

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

  loadDataFromServer(
    pageIndex: number,
    pageSize: number,
  ): void {
    this.loading = true;
    this.pubService.getGroupUser(this.group.id, 'id', pageIndex, pageSize).subscribe(data => {
        this.loading = false;
        this.total = 200; // mock the total data here
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
}
