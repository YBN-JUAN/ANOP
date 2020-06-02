import {Component, OnInit} from '@angular/core';
import {NzTableQueryParams} from 'ng-zorro-antd/table';
import {GroupInfoModel} from '../../../../share/model/group-info.model';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {NzMessageService, NzModalService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-publish-center-group-list',
  templateUrl: './publish-center-group-list.component.html'
})

export class PublishCenterGroupListComponent implements OnInit {
  total = 1;
  listOfGroups: GroupInfoModel[] = [];
  loading = true;
  pageSize = 6;
  pageIndex = 1;
  buttonTitle = '切换到我管理的群组列表';
  listType = 0;

  constructor(private service: PublishCenterService, private modal: NzModalService, private msg: NzMessageService) {
  }

  loadDataFromServer(
    pageIndex: number,
    pageSize: number,
  ): void {
    this.loading = true;
    this.service.getGroupsByType(this.listType, 'id', pageIndex, pageSize).subscribe(data => {
        this.loading = false;
        this.total = data.total;
        this.listOfGroups = data.list;
        console.log(this.listOfGroups);
        console.log(data);
      },
      (response: HttpErrorResponse) => {
        console.log(response);
        this.msg.error(response.error.message);
      }
    );
  }

  onQueryParamsChange(params: NzTableQueryParams): void {
    console.log(params);
    const {pageSize, pageIndex} = params;
    this.loadDataFromServer(pageIndex, pageSize);
  }

  dismiss(id: number): void {
    this.modal.confirm({
      nzTitle: '您确定要解散这个群组吗?',
      // nzContent: '<b style="color: red;">Some descriptions</b>',
      nzOkText: '确定',
      nzOkType: 'danger',
      nzOnOk: () => {
        this.service.dismissGroup(id).subscribe(
          () => {
            this.service.msg.success('您已解散该群')
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

  changeList() {
    if (this.listType === 0) {
      this.buttonTitle = '切换到我创建的群组列表';
      this.listType = 1;
    } else {
      this.buttonTitle = '切换到我管理的群组列表';
      this.listType = 0;
    }
    this.pageIndex = 1;
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }

  ngOnInit(): void {
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }
}
