import {Component, OnInit} from '@angular/core';
import {NzModalService, NzTableQueryParams} from 'ng-zorro-antd';
import {UserRequest} from '../../../../share/model/user-request';
import {UserRequestService} from '../../../../share/service/user-request.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {
  total = 1;
  requestList: UserRequest[] = [];
  loading = true;
  pageSize = 6;
  pageIndex = 1;
  buttonTitle = '切换到我管理的群组的用户请求';
  listType = 0;

  constructor(private service: UserRequestService,
              private route: Router,
              private modal: NzModalService) {
  }

  loadDataFromServer(
    pageIndex: number,
    pageSize: number,
  ): void {
    this.loading = true;
    this.service.getUserRequest(this.listType, 'id', pageIndex, pageSize).subscribe(data => {
        this.loading = false;
        this.total = data.total;
        this.requestList = data.list;
        console.log(this.requestList);
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

  changeList() {
    if (this.listType === 0) {
      this.buttonTitle = '切换到我创建的群组的用户请求';
      this.listType = 1;
    } else {
      this.buttonTitle = '切换到我管理的群组的用户请求';
      this.listType = 0;
    }
    this.pageIndex = 1;
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }

  dealAll(isAccepted: number) {
    let title: string;
    if (isAccepted === 1) {
      title = '你确定要接受所有用户的请求吗?';
    } else {
      title = '你确定要拒绝所有用户的请求吗?';
    }
    this.modal.confirm({
      nzTitle: title,
      nzOkText: '确定',
      nzOkType: 'danger',
      nzOnOk: () => {
        for (const req of this.requestList) {
          this.service.dealRequest(req.id, isAccepted).subscribe(
            response => {
              console.log(response)
            });
        }
        // this.ngOnInit();
        location.reload();
      },
      nzCancelText: '取消',
      nzOnCancel: () => console.log('Cancel')
    });
  }

  dealOne(id: number, isAccepted: number) {
    let title: string;
    if (isAccepted === 1) {
      title = '你确定要接受该用户的请求吗?';
    } else {
      title = '你确定要拒绝该用户的请求吗?';
    }
    this.modal.confirm({
      nzTitle: title,
      nzOkText: '确定',
      nzOkType: 'danger',
      nzOnOk: () => {
        this.service.dealRequest(id, isAccepted).subscribe(
          response => {
            console.log(response);
          },
          error => {
            console.log(error);
          }
          );
        location.reload();  // 刷新页面
        this.loadDataFromServer(this.pageIndex, this.pageSize);
      },
      nzCancelText: '取消',
      nzOnCancel: () => console.log('Cancel')
    });
  }

  ngOnInit(): void {
    this.loadDataFromServer(this.pageIndex, this.pageSize);
  }
}
