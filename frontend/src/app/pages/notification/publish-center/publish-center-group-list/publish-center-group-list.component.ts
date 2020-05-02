import { Component, OnInit } from '@angular/core';
import { NzTableQueryParams } from 'ng-zorro-antd/table';
import {Group} from '../../../../share/model/group-info';
import {PublishCenterService} from '../../../../share/service/publish-center.service';
import {NzModalService} from 'ng-zorro-antd';

@Component({
  selector: 'app-publish-center-group-list',
  templateUrl: './publish-center-group-list.component.html'
})

export class PublishCenterGroupListComponent implements OnInit {
  total = 1;
  listOfGroups: Group[] = [];
  loading = true;
  pageSize = 10;
  pageIndex = 1;
  buttonTitle = "切换到我管理的群组列表";
  listType = 0;

  loadDataFromServer(
    pageIndex: number,
    pageSize: number,
  ): void {
    this.loading = true;
    this.service.getGroups(this.listType,"id", pageIndex, pageSize).subscribe(data => {
      this.loading = false;
      this.total = 200; // mock the total data here
      this.listOfGroups = data.list;
      console.log(this.listOfGroups);
      console.log(data);
      },
      error => { console.log(error); }
    );
  }

  onQueryParamsChange(params: NzTableQueryParams): void {
    console.log(params);
    const { pageSize, pageIndex} = params;
    this.loadDataFromServer(pageIndex, pageSize);
  }

  deletGroup(id: number): void {
    this.modal.confirm({
      nzTitle: '你确定要删除这个群组吗?',
      //nzContent: '<b style="color: red;">Some descriptions</b>',
      nzOkText: '确定',
      nzOkType: 'danger',
      nzOnOk: () => {
        this.service.deleteGroup(id);
        this.ngOnInit();
      },
      nzCancelText: '取消',
      nzOnCancel: () => console.log('Cancel')
    });
  }

  changeList() {
    if (this.listType == 0) {
      this.buttonTitle = "切换到我创建的群组列表";
      this.listType = 1;
    } else {
      this.buttonTitle = "切换到我管理的群组列表";
      this.listType = 0;
    }
    this.pageIndex = 1;
    this.loadDataFromServer(this.pageIndex, this.pageSize );
  }

  constructor(private service: PublishCenterService,
              private modal: NzModalService) {}

  ngOnInit(): void {
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }
}