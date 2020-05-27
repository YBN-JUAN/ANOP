import {Component, OnInit} from '@angular/core';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {GroupUser} from '../../../../share/model/user-info.model';
import {ActivatedRoute} from '@angular/router';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {NzModalService, NzTableQueryParams, toNumber} from 'ng-zorro-antd';

@Component({
  selector: 'app-publish-center-group-detail',
  templateUrl: './publish-center-group-detail.component.html',
  styleUrls: ['./publish-center-group-detail.component.css']
})
export class PublishCenterGroupDetailComponent implements OnInit {

  public group: GroupInfoModel;
  total = 1;
  listOfUsers: GroupUser[] = [];
  loading = true;
  pageSize = 3;
  pageIndex = 1;

  constructor(private route: ActivatedRoute,
              private service: PublishCenterService,
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
    this.service.getGroup(id).subscribe(
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

  dismissGroup() {
    this.modal.confirm({
      nzTitle: '你确定要解散这个群组吗?',
      // nzContent: '<b style="color: red;">Some descriptions</b>',
      nzOkText: '确定',
      nzOkType: 'danger',
      nzOnOk: () => {
        this.service.dismissGroup(this.group.id);
        this.ngOnInit();
      },
      nzCancelText: '取消',
      nzOnCancel: () => console.log('Cancel')
    });
  }

  loadDataFromServer(pageIndex: number, pageSize: number): void {
    this.loading = true;
    this.service.getGroupUser(this.group.id, 'id', pageIndex, pageSize).subscribe(data => {
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
}
